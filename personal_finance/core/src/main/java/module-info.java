module personal_finance.core {
  requires com.fasterxml.jackson.core;
  requires transitive com.fasterxml.jackson.databind;

  exports personal_finance.core;
  exports personal_finance.json;
}
