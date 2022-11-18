module personal_finance.rest {
  requires com.fasterxml.jackson.databind;

  requires spring.web;
  requires spring.beans;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;

  requires transitive personal_finance.core;

  exports personal_finance.restserver;

  opens personal_finance.restserver to spring.beans, spring.context, spring.web;
}
