// Função para carregar a lista de escolas
function carregarEscolas() {
    const listaEscolas = document.getElementById('lista-escolas');

    // Limpa a lista antes de carregar novos resultados
    listaEscolas.innerHTML = '';

    // Faz a requisição ao back-end para buscar escolas
    fetch('/escolas')
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar escolas: ' + response.statusText);
            }
            return response.json();
        })
        .then(escolas => {
            // Exibe as escolas na lista
            escolas.forEach(escola => {
                const item = document.createElement('li');
                item.textContent = `${escola.nome} - ${escola.endereco} (Contato: ${escola.contato})`;
                listaEscolas.appendChild(item);
            });

            // Exibe uma mensagem se nenhuma escola for encontrada
            if (escolas.length === 0) {
                const item = document.createElement('li');
                item.textContent = 'Nenhuma escola cadastrada.';
                listaEscolas.appendChild(item);
            }
        })
        .catch(error => {
            console.error('Erro ao carregar escolas:', error);
            const item = document.createElement('li');
            item.textContent = 'Erro ao carregar escolas.';
            listaEscolas.appendChild(item);
        });
}

// Função para cadastrar uma nova escola
document.getElementById('form-escola').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio tradicional do formulário

    // Captura os dados do formulário
    const escola = {
        nome: document.getElementById('nome').value,
        endereco: document.getElementById('endereco').value,
        contato: document.getElementById('contato').value
    };

    // Envia os dados para o back-end
    fetch('/escolas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(escola)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(errorMessage => {
                throw new Error(errorMessage);
            });
        }
        return response.json();
    })
    .then(data => {
        alert('Escola cadastrada com sucesso!');
        document.getElementById('form-escola').reset(); // Limpa o formulário
        carregarEscolas(); // Atualiza a lista de escolas
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao cadastrar escola: ' + error.message);
    });
});

// Carrega a lista de escolas quando a página é carregada
document.addEventListener('DOMContentLoaded', carregarEscolas);