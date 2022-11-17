module personal_finance.fxutil {
    requires com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    requires java.net.http;

    requires transitive personal_finance.core;
    requires personal_finance.rest;

    exports personal_finance.util;
}
