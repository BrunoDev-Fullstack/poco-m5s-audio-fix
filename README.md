# POCO M5s Audio Fix

Solução Android para restaurar o áudio do alto-falante inferior do POCO M5s.

**Autor:** Bruno Ribeiro  
**Dispositivo testado:** POCO M5s  
**Sistema:** Android 13 / MIUI 14  
**Status:** workaround funcional

## Sobre o projeto

Este projeto surgiu durante o diagnóstico de um POCO M5s que apresentava falha na reprodução de áudio.

Durante os testes, identifiquei que o sistema continuava processando o áudio, porém o alto-falante inferior não era inicializado corretamente pelo fluxo padrão do sistema.

O modo de diagnóstico do aparelho demonstrou que o alto-falante inferior continuava funcional quando uma rota específica de áudio era ativada.

A partir dessa descoberta, desenvolvi este aplicativo para aplicar a configuração necessária através do `AudioManager`.

## Solução

O aplicativo aplica o parâmetro:

`play_select_spk=right-spk-factory`

No aparelho testado, esse parâmetro inicializa a rota utilizada pelo alto-falante inferior.

## Evolução

### v1.0.0 — Prova de conceito

Primeira versão criada para validar a solução.

A correção era aplicada quando o aplicativo era aberto manualmente.

### v2.0.0 — Correção automática

A segunda versão adicionou:

- `BootReceiver`
- `AudioFixService`
- Foreground Service
- execução em segundo plano
- inicialização após o boot
- reaplicação automática da configuração de áudio

## Configuração no MIUI

1. Instale o aplicativo.
2. Abra o POCO Audio Fix pelo menos uma vez.
3. Ative o início automático em segundo plano.
4. Configure a bateria como `Sem restrições`.
5. Reinicie o aparelho.
6. Aguarde alguns segundos após a inicialização.

## Segurança

O aplicativo:

- não requer root;
- não desbloqueia o bootloader;
- não realiza flash;
- não modifica a ROM;
- não altera arquivos em `/system` ou `/vendor`;
- não utiliza conexão com a Internet.

## Limitações

Este projeto é um workaround de software desenvolvido e testado em um POCO M5s específico.

Ele não substitui diagnóstico ou reparo físico caso exista defeito de hardware.

## Aviso

Projeto independente, sem vínculo, suporte ou afiliação com Xiaomi, POCO ou MediaTek.

## Autor

**Bruno Ribeiro**
