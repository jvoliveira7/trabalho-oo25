const API_URL = "http://localhost:7000/professores";

// Função para carregar a lista de professores
async function listarProfessores() {
    try {
        const resposta = await fetch(API_URL);
        if (!resposta.ok) throw new Error("Erro ao listar professores");
        
        const professores = await resposta.json();
        atualizarTabela(professores);
    } catch (erro) {
        console.error(erro);
    }
}

// Função para atualizar a tabela com os professores
function atualizarTabela(professores) {
    const tabela = document.getElementById("tabelaProfessores");
    tabela.innerHTML = ""; // Limpa a tabela

    professores.forEach(professor => {
        const linha = document.createElement("tr");

        linha.innerHTML = `
            <td>${professor.siape}</td>
            <td>${professor.nome}</td>
            <td>${professor.cpf}</td>
            <td>${professor.dataNascimento}</td>
            <td>${professor.endereco}</td>
            <td>${professor.telefone}</td>
            <td>${professor.email}</td>
            <td><button onclick="excluirProfessor('${professor.siape}')">Excluir</button></td>
        `;

        tabela.appendChild(linha);
    });
}

// Função para cadastrar um novo professor
document.getElementById("formProfessor").addEventListener("submit", async function(event) {
    event.preventDefault();

    const professor = {
        siape: document.getElementById("siape").value,
        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value,
        dataNascimento: document.getElementById("dataNascimento").value,
        endereco: document.getElementById("endereco").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value
    };

    try {
        const resposta = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(professor)
        });

        if (!resposta.ok) throw new Error("Erro ao cadastrar professor");

        listarProfessores(); // Atualiza a lista
        this.reset(); // Limpa o formulário
    } catch (erro) {
        console.error(erro);
    }
});

// Função para excluir um professor pelo SIAPE
async function excluirProfessor(siape) {
    if (!confirm("Tem certeza que deseja excluir este professor?")) return;

    try {
        const resposta = await fetch(`${API_URL}/${siape}`, { method: "DELETE" });

        if (!resposta.ok) throw new Error("Erro ao excluir professor");

        listarProfessores(); // Atualiza a lista
    } catch (erro) {
        console.error(erro);
    }
}

// Carregar a lista ao iniciar
listarProfessores();
