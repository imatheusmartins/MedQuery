function adicionarSintoma() {
    const sintoma = document.getElementById("sintoma").value;
    const gravidade = document.getElementById("gravidade").value;

    if (!sintoma || !gravidade) {
        alert("Por favor, selecione um sintoma e a gravidade.");
        return;
    }

    const tabela = document.getElementById("tabelaSintomas").getElementsByTagName('tbody')[0];
    const novaLinha = tabela.insertRow();

    // Criação das células da linha
    const celulaSintoma = novaLinha.insertCell(0);
    const celulaGravidade = novaLinha.insertCell(1);
    const celulaAcoes = novaLinha.insertCell(2);

    // Preenchendo as células
    celulaSintoma.textContent = sintoma;
    celulaGravidade.textContent = gravidade;
    celulaAcoes.innerHTML = `
        <span class="acao" onclick="editarSintoma(this)">✏️ Editar</span> | 
        <span class="acao" onclick="removerSintoma(this)">❌ Excluir</span>
    `;

    // Limpar campos
    document.getElementById("sintoma").value = "";
    document.getElementById("gravidade").value = "";
}

function removerSintoma(element) {
    const linha = element.parentNode.parentNode;
    linha.remove();
}

function editarSintoma(element) {
    const linha = element.parentNode.parentNode;
    const sintoma = linha.cells[0].textContent;
    const gravidade = linha.cells[1].textContent;

    // Preenchendo os campos com os valores da linha
    document.getElementById("sintoma").value = sintoma;
    document.getElementById("gravidade").value = gravidade;

    // Removendo a linha para atualizar com o novo valor
    linha.remove();
}
