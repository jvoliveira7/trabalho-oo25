function listarAlunos() {
    const listaAlunos = document.getElementById('lista-alunos');
    listaAlunos.innerHTML = '';

    fetch('/alunos')
        .then(response => response.json())
        .then(alunos => {
            alunos.forEach(aluno => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${aluno.nome}</td>
                    <td>${aluno.matricula}</td>
                    <td>
                        <button class="excluir-button" onclick="excluirAluno(${aluno.matricula})">Excluir</button>
                    </td>
                `;
                listaAlunos.appendChild(tr);
            });

            if (alunos.length === 0) {
                const tr = document.createElement('tr');
                tr.innerHTML = '<td colspan="3">Nenhum aluno cadastrado.</td>';
                listaAlunos.appendChild(tr);
            }
        })
        .catch(error => {
            const tr = document.createElement('tr');
            tr.innerHTML = '<td colspan="3">Erro ao carregar alunos.</td>';
            listaAlunos.appendChild(tr);
        });
}

function excluirAluno(matricula) {
    if (confirm('Tem certeza que deseja excluir este aluno?')) {
        fetch(`/alunos/${matricula}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Aluno excluído com sucesso!');
                listarAlunos();
            } else {
                alert('Erro ao excluir aluno.');
            }
        })
        .catch(error => {
            alert('Erro ao excluir aluno. Verifique o console para mais detalhes.');
        });
    }
}

document.getElementById('form-cadastro-aluno').addEventListener('submit', function (event) {
    event.preventDefault();

    const matricula = parseInt(document.getElementById('matricula').value);
    const nome = document.getElementById('nome').value.trim();
    const cpf = document.getElementById('cpf').value.trim();
    const dataNascimento = document.getElementById('dataNascimento').value;
    const endereco = document.getElementById('endereco').value.trim();
    const telefone = document.getElementById('telefone').value.trim();
    const email = document.getElementById('email').value.trim();

    if (!matricula || !nome || !cpf || !dataNascimento || !endereco || !telefone || !email) {
        alert("Todos os campos devem ser preenchidos.");
        return;
    }

    const aluno = {
        matricula,
        nome,
        cpf,
        dataNascimento,
        endereco,
        telefone,
        email
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
            return response.text();  
        } 
    })
    .then(responseText => {
        if (typeof responseText === 'string') {
            alert('Aluno cadastrado com sucesso!');
            document.getElementById('form-cadastro-aluno').reset();  
            listarAlunos();  
        } else {
            alert(`Erro ao cadastrar aluno: ${responseText.message || 'Desconhecido'}`);
        }
    })
    .catch(error => {
        alert(`Erro ao cadastrar aluno. Verifique o console para mais detalhes.`);
    });
});

document.addEventListener('DOMContentLoaded', listarAlunos);
