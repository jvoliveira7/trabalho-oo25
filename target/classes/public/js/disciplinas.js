// Função para carregar a lista de disciplinas
function carregarDisciplinas() {
    const listaDisciplinas = document.getElementById('lista-disciplinas');

    // Limpa a lista antes de carregar novos resultados
    listaDisciplinas.innerHTML = '';

    // Faz a requisição ao back-end para buscar disciplinas
    fetch('/disciplinas')
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar disciplinas: ' + response.statusText);
            }
            return response.json();
        })
        .then(disciplinas => {
            if (disciplinas.length === 0) {
                const item = document.createElement('li');
                item.textContent = 'Nenhuma disciplina cadastrada.';
                listaDisciplinas.appendChild(item);
                return;
            }

            // Exibe as disciplinas na lista (sem botão de edição ou exclusão)
            disciplinas.forEach(disciplina => {
                const item = document.createElement('li');
                item.innerHTML = `<strong>Código:</strong> ${disciplina.codigo} <br>
                                  <strong>Nome:</strong> ${disciplina.nome} <br>
                                  <strong>Professor:</strong> ${disciplina.siapeProfessor} <br>
                                  <strong>Carga Horária:</strong> ${disciplina.cargaHoraria}h <br>
                                  <strong>Ementa:</strong> ${disciplina.ementa}`;
                listaDisciplinas.appendChild(item);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar disciplinas:', error);
            const item = document.createElement('li');
            item.textContent = 'Erro ao carregar disciplinas.';
            listaDisciplinas.appendChild(item);
        });
}

// Função para cadastrar uma nova disciplina
document.getElementById('form-disciplina').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio tradicional do formulário

    // Captura os dados do formulário
    const disciplina = {
        codigo: document.getElementById('codigo').value.trim(),
        nome: document.getElementById('nome').value.trim(),
        siapeProfessor: document.getElementById('professor').value.trim(),
        cargaHoraria: parseInt(document.getElementById('cargaHoraria').value.trim(), 10),
        ementa: document.getElementById('ementa').value.trim()
    };

    if (!disciplina.codigo || !disciplina.nome || !disciplina.siapeProfessor || !disciplina.cargaHoraria || !disciplina.ementa) {
        alert('Preencha todos os campos!');
        return;
    }

    // Envia os dados para o back-end
    fetch('/disciplinas', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(disciplina)
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
        alert('Disciplina cadastrada com sucesso!');
        document.getElementById('form-disciplina').reset(); // Limpa o formulário
        carregarDisciplinas(); // Atualiza a lista de disciplinas
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao cadastrar disciplina: ' + error.message);
    });
});

// Função para excluir uma disciplina pelo código
function excluirDisciplina() {
    const codigo = document.getElementById('codigo-excluir').value.trim(); // Captura o código digitado no campo

    if (!codigo) {
        alert('Por favor, insira o código da disciplina para exclusão!');
        return;
    }

    fetch(`/disciplinas/${codigo}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) {
                // Caso a resposta não seja ok, tenta pegar a mensagem de erro
                return response.json().then(errorMessage => {
                    throw new Error(errorMessage || 'Erro desconhecido ao excluir disciplina!');
                });
            }
            return response.text(); // Caso a exclusão tenha sido bem-sucedida
        })
        .then(() => {
            alert('Disciplina excluída com sucesso!');
            document.getElementById('codigo-excluir').value = ''; // Limpa o campo
            carregarDisciplinas(); // Atualiza a lista de disciplinas
        })
        .catch(error => {
            console.error('Erro ao excluir disciplina:', error);
            alert('Erro ao excluir disciplina: ' + error.message);
        });
}


// Carrega a lista de disciplinas quando a página é carregada
document.addEventListener('DOMContentLoaded', carregarDisciplinas);
