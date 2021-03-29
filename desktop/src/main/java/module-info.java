/**
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-29
 */
module desktop {

    requires system.core;

    requires javax.inject;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires java.sql;

    requires javafx.controls;

    opens dev.brunocesar.bank.desktop.screen;
    opens dev.brunocesar.bank.desktop.dev;
    opens dev.brunocesar.bank.desktop.hml;

    requires services;
    requires spring.jdbc;
    requires hsqldb;

}