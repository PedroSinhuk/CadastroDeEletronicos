'use strict';
(() => {
    const base = location.pathname.split('/marca/ui')[0];
    const catalog = `${base}/marca/ui/listar`;
    const normalizeText = text => text.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLocaleLowerCase('pt-BR');
    const search = document.querySelector('#brand-search');
    if (search) {
        const rows = [...document.querySelectorAll('#brands tr')];
        const filter = () => {
            const query = normalizeText(search.value.trim());
            rows.forEach(row => { row.hidden = !normalizeText(row.dataset.search).includes(query); });
            const count = rows.filter(row => !row.hidden).length;
            document.querySelector('#brand-results').textContent = `Exibindo ${count} de ${rows.length} marca${rows.length === 1 ? '' : 's'}`;
            document.querySelector('#brand-empty').hidden = count > 0;
            if (rows.length) {
                document.querySelector('#brand-empty-title').textContent = 'Nenhuma marca encontrada';
                document.querySelector('#brand-empty-description').textContent = 'Tente buscar por outro nome ou ID.';
                document.querySelector('#brand-empty a').hidden = true;
            }
        };
        search.addEventListener('input', filter);
        filter();
    }

    const form = document.querySelector('#brand-form');
    if (form) form.addEventListener('submit', async event => {
        event.preventDefault();
        const error = document.querySelector('#brand-form-error');
        const button = document.querySelector('#brand-save');
        error.textContent = '';
        const nome = form.elements.nome.value.trim();
        if (!nome) {
            error.textContent = 'Informe o nome da marca.';
            form.elements.nome.focus();
            return;
        }
        const id = form.dataset.id;
        button.disabled = true;
        button.textContent = 'Salvando…';
        try {
            const response = await fetch(`${base}/marca/${id ? `alterarID/${encodeURIComponent(id)}` : 'criarMarca'}`, {
                method: id ? 'PUT' : 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({nome})
            });
            if (!response.ok) throw new Error(response.status === 404 ? 'A marca não foi encontrada. Volte à listagem e atualize a página.' : 'Não foi possível salvar a marca. Tente novamente.');
            location.assign(`${catalog}?salvo`);
        } catch (failure) {
            error.textContent = failure instanceof TypeError ? 'Falha na conexão com o servidor. Tente novamente.' : failure.message;
            button.disabled = false;
            button.textContent = 'Salvar marca';
        }
    });

    const dialog = document.querySelector('#brand-delete-dialog');
    if (!dialog) return;
    let selectedId;
    let deleting = false;
    const confirmButton = document.querySelector('#brand-confirm-delete');
    document.querySelectorAll('[data-brand-delete]').forEach(button => button.addEventListener('click', () => {
        selectedId = button.dataset.brandDelete;
        document.querySelector('#brand-delete-name').textContent = button.dataset.name;
        document.querySelector('#brand-delete-error').textContent = '';
        dialog.showModal();
    }));
    dialog.addEventListener('cancel', event => { if (deleting) event.preventDefault(); });
    confirmButton.addEventListener('click', async () => {
        deleting = true;
        dialog.querySelectorAll('button').forEach(button => { button.disabled = true; });
        confirmButton.textContent = 'Excluindo…';
        try {
            const response = await fetch(`${base}/marca/deletarID/${encodeURIComponent(selectedId)}`, {method: 'DELETE'});
            if (!response.ok) throw new Error(response.status === 404 ? 'A marca não foi encontrada. Atualize a listagem.' : 'Não foi possível excluir. Verifique se há dispositivos vinculados à marca e tente novamente.');
            location.assign(`${catalog}?excluido`);
        } catch (failure) {
            document.querySelector('#brand-delete-error').textContent = failure instanceof TypeError ? 'Falha na conexão com o servidor. Tente novamente.' : failure.message;
            deleting = false;
            dialog.querySelectorAll('button').forEach(button => { button.disabled = false; });
            confirmButton.textContent = 'Sim, excluir';
        }
    });
})();
