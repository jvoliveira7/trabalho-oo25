
function listarAlunos() {
    const listaAlunos = document.getElementById('lista-alunos');

    listaAlunos.innerHTML = '';

   
    fetch('/alunos')
        .then(response => response.json())
        .then(alunos => {
   
            alunos.forEach(aluno => {
                const item = document.createElement('li');
                item.textContent = `${aluno.nome} (Matrícula: ${aluno.matricula})`;
                listaAlunos.appendChild(item);
            });

            if (alunos.length === 0) {
                const item = document.createElement('li');
                item.textContent = 'Nenhum aluno cadastrado.';
                listaAlunos.appendChild(item);
            }
        })
        .catch(error => {
            console.error('Erro ao carregar alunos:', error);
            const item = document.createElement('li');
            item.textContent = 'Erro ao carregar alunos.';
            listaAlunos.appendChild(item);
        });
}


document.getElementById('form-cadastro-aluno').addEventListener('submit', function (event) {
    event.preventDefault(); 


    const aluno = {
        matricula: parseInt(document.getElementById('matricula').value),
        nome: document.getElementById('nome').value,
        cpf: document.getElementById('cpf').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        endereco: document.getElementById('endereco').value,
        telefone: document.getElementById('telefone').value,
        email: document.getElementById('email').value
    };


    fetch('/alunos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(aluno)
    })
    .then(response => {
        if (response.ok) {
            alert('Aluno cadastrado com sucesso!');
            document.getElementById('form-cadastro-aluno').reset(); 
            listarAlunos(); 
            response.text().then(errorMessage => {
                alert('Erro ao cadastrar aluno: ' + errorMessage);
            });
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao cadastrar aluno. Verifique o console para mais detalhes.');
    });
});


document.addEventListener('DOMContentLoaded', listarAlunos);