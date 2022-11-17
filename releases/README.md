# Releases

**Info on how to package**
 * Find your way to the LocalAccess branch and access it through gitpod or locally on your pc
 * cd into personal_finance folder
 * Run 'mvn clean install compile'
 * Then run 'mvn javafx:jlink -f fxui/pom.xml'
 * Finally run 'mvn jpackage:jpackage -f fxui/pom.xml'
 * The installer for your OS will recide in personal_finance/fxui/target/dist/

**Installers**
 * [Linux (deb)](personal-finance_1.0.0-1_amd64)