# FastLaunch

Mod Fabric (Minecraft 1.21.1, Java 21) com ajustes leves para abrir o jogo mais rapido.

## O que ele faz hoje
- Remove o fade-in da tela de titulo (`skipTitleFade`, ligado por padrao).
- Opcional (`tuneVideoOptions=true`): aplica biome blend baixo e desliga sombras de entidades ao iniciar.
- Config em `config/fastlaunch.properties` (criada no primeiro start).

## Compilar
```
gradle wrapper      # ou copie o wrapper do template oficial do Fabric
./gradlew build
```
O jar sai em `build/libs/fastlaunch-1.0.0.jar`. Precisa de internet e JDK 21.

## Onde esta o ganho de verdade: argumentos da JVM
Cole no launcher (argumentos da JVM). Ajuste -Xmx para ~50% da sua RAM:
```
-Xms2G -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+AutoCreateSharedArchive -XX:SharedArchiveFile=minecraft.jsa -Dmax.bg.threads=8
```
`AutoCreateSharedArchive` (JDK 19+) cria um cache de classes na primeira abertura e acelera as seguintes.

## Mods que ja resolvem o resto (use junto)
Sodium (video), Lithium (logica do servidor/mundo), FerriteCore (memoria), ModernFix (inicio e carregamento de mundo mais rapidos), Entity Culling.
