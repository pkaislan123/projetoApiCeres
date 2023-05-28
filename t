import requests
import time

# Função para listar as tarefas e verificar se é necessário fazer o download do áudio
def listar_tarefas():
    url = "http://localhost:8080/v1/protected/modulosonoro/tarefas/123456"
    response = requests.get(url)
    if response.status_code == 200:
        tarefa = response.json()

        # Verificar se há tarefas não respondidas com tipo_requisicao igual a 0
        if tarefa["respondido_modulo_sonoro"] == 0 and tarefa["tipo_requisicao"] == 0:
            audio_id = tarefa["audio"]["id_audio"]
            download_audio(audio_id)
        else:
            print("Não há tarefas pendentes de download de áudio.")
    else:
        print("Erro ao obter a lista de tarefas.")

# Função para fazer o download do arquivo de áudio
def download_audio(audio_id):
    url = f"http://localhost:8080/v1/protected/modulosonoro/download/{audio_id}"
    response = requests.get(url)
    if response.status_code == 200:
        # Nome do arquivo de áudio
        nome_arquivo = response.headers.get('Content-Disposition').split('filename=')[1]
        # Caminho de destino para salvar o arquivo
        caminho_destino = f"C:/musicasrecebidas/{nome_arquivo}"
        
        # Salvar o arquivo no caminho de destino
        with open(caminho_destino, 'wb') as file:
            file.write(response.content)
        
        print(f"Arquivo de áudio salvo em: {caminho_destino}")
    else:
        print("Erro ao fazer o download do arquivo de áudio")

# Loop principal para listar as tarefas a cada 30 segundos
while True:
    listar_tarefas()
    time.sleep(30)
