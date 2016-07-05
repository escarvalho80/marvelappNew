package br.com.frameworksystem.marvelapp.util;

/**
 * Created by User on 04/07/2016.
 */
public interface Constante {

    String CHARATER_TABLE = "character";
    String EVENT_TABLE = "event";

    String DDL_CHARACTER = "CREATE TABLE [character] (\n" +
            " [id] INTEGER IDENTITY (1,1), \n" +
            " [name] VARCHAR(100), \n" +
            " [description] TEXT, \n" +
            " [link] TEXT, \n" +
            " [image] TEXT, \n" +
            " [favorite] INTEGER DEFAULT 0, \n" +
            " CONSTRAINT [] PRIMARY KEY ([id]));\n"
            ;

    String DDL_EVENT = "CREATE TABLE [event] (\n" +
            " [id] INTEGER IDENTITY (1,1), \n" +
            " [name] VARCHAR(100), \n" +
            " [description] TEXT, \n" +
            " [image] TEXT, \n" +
            " [link] TEXT, \n" +
            " CONSTRAINT [] PRIMARY KEY ([id]));\n"
            ;
}
