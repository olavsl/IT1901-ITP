module personal_finance.restserver {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires personal_finance.core;

    opens personal_finance.restserver to spring.beans, spring.context, spring.web;

}
