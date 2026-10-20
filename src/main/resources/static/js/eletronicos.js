'use strict';
const uiBase = location.pathname.slice(0, location.pathname.indexOf('/eletronicos/ui'));
const catalogUrl = `${uiBase}/eletronicos/ui/listar`;
const currency = new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'});
document.querySelectorAll('[data-money]').forEach(el => { if (el.dataset.money !== '') el.textContent = currency.format(Number(el.dataset.money)); });
const rows = [...document.querySelectorAll('#devices tr')];
const normalize = text => text.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLocaleLowerCase('pt-BR');
if (document.querySelector('#devices')) {
    document.querySelector('#category-count').textContent = new Set(rows.map(row => row.dataset.category).filter(Boolean)).size;
    document.querySelector('#total-value').textContent = currency.format(rows.reduce((sum, row) => sum + Math.round(Number(row.dataset.value || 0) * 100), 0) / 100);
    const filter = () => {
        const search = normalize(document.querySelector('#search').value.trim());
        const category = document.querySelector('#category').value;
        rows.forEach(row => { row.hidden = !normalize(row.dataset.search).includes(search) || (!!category && row.dataset.category !== category); });
        const visible = rows.filter(row => !row.hidden).length;
        document.querySelector('#results').textContent = `Exibindo ${visible} de ${rows.length} eletrônico${rows.length === 1 ? '' : 's'}`;
        document.querySelector('#empty').hidden = visible > 0;
        if (rows.length) {
            document.querySelector('#empty-title').textContent = 'Nenhum eletrônico encontrado';
            document.querySelector('#empty-description').textContent = 'Tente outro nome ou selecione uma categoria diferente.';
            document.querySelector('#empty a').hidden = true;
        }
    };
    document.querySelector('#search').addEventListener('input', filter);
    document.querySelector('#category').addEventListener('change', filter);
    filter();
}
const form = document.querySelector('#device-form');
if (form) form.addEventListener('submit', async event => {
    event.preventDefault();
    const error = document.querySelector('#form-error');
    const button = document.querySelector('#save-button');
    error.textContent = '';
    const fields = new FormData(form);
    const nome = fields.get('nome').trim();
    if (!nome) { error.textContent = 'Informe o nome do eletrônico.'; form.elements.nome.focus(); return; }
    const payload = {nome, tipoEletronico: fields.get('tipoEletronico'), cor: fields.get('cor').trim() || null, valor: Number(fields.get('valor')), ano: fields.get('ano') ? Number(fields.get('ano')) : null, marca: form.dataset.brandId ? {id: Number(form.dataset.brandId)} : null};
    const id = form.dataset.id;
    button.disabled = true;
    button.textContent = 'Salvando…';
    try {
        const response = await fetch(`${uiBase}/eletronicos/${id ? `alterar/${encodeURIComponent(id)}` : 'criar'}`, {method: id ? 'PUT' : 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(payload)});
        if (!response.ok) throw new Error(response.status === 404 ? 'O eletrônico não foi encontrado. Volte ao catálogo e atualize a página.' : 'Não foi possível salvar. Confira os campos e tente novamente.');
        location.assign(`${catalogUrl}?salvo`);
    } catch (failure) {
        error.textContent = failure instanceof TypeError ? 'Não foi possível conectar ao servidor. Verifique sua conexão e tente novamente.' : failure.message;
        button.disabled = false;
        button.textContent = 'Salvar eletrônico';
    }
});
const dialog = document.querySelector('#delete-dialog');
let deleteId;
let deleting = false;
if (dialog) {
    document.querySelectorAll('[data-delete]').forEach(button => button.addEventListener('click', () => {
        deleteId = button.dataset.delete;
        document.querySelector('#delete-name').textContent = button.dataset.name;
        document.querySelector('#delete-error').textContent = '';
        dialog.showModal();
    }));
    dialog.addEventListener('cancel', event => { if (deleting) event.preventDefault(); });
    document.querySelector('#confirm-delete').addEventListener('click', async () => {
        deleting = true;
        dialog.querySelectorAll('button').forEach(button => { button.disabled = true; });
        try {
            const response = await fetch(`${uiBase}/eletronicos/deletar/${encodeURIComponent(deleteId)}`, {method: 'DELETE'});
            if (!response.ok) throw new Error('Não foi possível excluir o eletrônico. Tente novamente.');
            location.assign(`${catalogUrl}?excluido`);
        } catch (failure) {
            document.querySelector('#delete-error').textContent = failure instanceof TypeError ? 'Falha na conexão. Tente novamente.' : failure.message;
            deleting = false;
            dialog.querySelectorAll('button').forEach(button => { button.disabled = false; });
        }
    });
}
