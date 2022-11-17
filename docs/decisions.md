# Decisions

* The group decided that every new branch (feature/change) should be created from and Issue, and that the Issue should be closed when the branch is merged into main branch.
* Every commit message should be written in english
* The app will be in english in it's entirety
* The group decided to use the modules Checkstyle, Spotbugs and Jacoco for test coverage and code checks. See below for how to use these and decicions related to them.

## Checkstyle:

The group has let the following warnings remain:

* [MissingJavadocMethod] - Javadoc wants each parameter to be followed by documentation, but we do not deem this necessary in parameters such as username and password
* [PackageName] - Our package consists of a “_”, which is not according to the checkstyle practice. We won’t change it for now, but keep in mind for coming projects
* [RequireEmptyLineBeforeBlockTagGroup] - checkstyle requires an “empty” line before @return and @param parameters, which it doesn’t seem to detect, even though it is contained in the code
* [LineLength] - Some of our code lines extend over 100 lines - which is not a severe error
* [VariableDeclarationUsageDistance] - Some function doesn’t use the declared variable before 3 lines into the function, which is a requirement by the checkstyle config.
* Ideally we would have custom configurated checkstyle, but the standard google_checks gives a lot of valuable information anyways

## Spotbugs:

* Through finding bugs with spotbugs, a lot of bugfixes has been made. Several of the bugs were nonsense and it led to the exclusions of quite a few classes. These were still unexcluded throughout the code being updated to find new bugs to fix.
* Spotbugs makes the build fail, so as per release 3 the remaining bugs will be excluded.

## Jacoco:

With a total coverage of: ______________________

* Getter- and Setter-functions have been downprioritized in making tests for, as these are simple and safe in practice

## .mailmap:

* Maps the various author’s email to the a single name. This is because we in the beginning of the project ended up using gitlab with different accounts.

## Packaging of "shipable product":
 * The group decided to include prepackaged installers for linux (deb) and windows 10 for anyone who would want a LocalAccess version of the application eventhough its been depricated, as on a functional basis its identical to master.
 * Therefore jlink and jpackage, which was used for this, is only to be implemented in that seperate brach called LocalAccess
 * See [releases](../../releases/README.md) for info on how to package yourself or to find the installers