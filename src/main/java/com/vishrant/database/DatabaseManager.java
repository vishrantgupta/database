package com.vishrant.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.vishrant.database.config.DBConfiguration;

public class DatabaseManager implements Database {

	private static class SingletonHelper {
		private static final DatabaseManager INSTANCE = new DatabaseManager();
	}

	public static DatabaseManager getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private final Map<String, String> map = new HashMap<String, String>();
	private DBConfiguration dbConfiguration = null;

	private DatabaseManager() {
		if (!this.loadProperties()) {
			throw new ExceptionInInitializerError(
					"Error while loading database property files, check if database resource exists");
		}
	}

	public DBConfiguration getDBConfiguration() {
		return this.dbConfiguration;
	}

	@Override
	public String getQuery(final String id) {
		return this.map.get(id);
	}

	private boolean loadProperties() {

		final ClassLoader classLoader = this.getClass().getClassLoader();
		try {
			final File dbDir = new File(classLoader.getResource(
					"database/queries/").getFile());

			String data = null;
			if ((dbDir != null) && dbDir.isDirectory()) {

				final File[] tableFiles = dbDir.listFiles();

				final Set<TableQueries> tables = new HashSet<TableQueries>();

				for (final File file : tableFiles) {
					data = IOUtils.toString(new FileInputStream(file));

					final XStream xstream = new XStream();
					xstream.processAnnotations(TableQueries.class);

					final TableQueries queries = (TableQueries) xstream
							.fromXML(data);

					tables.add(queries);
				}

				for (final TableQueries tableQueries : tables) {
					try {
						final Map<String, String> allQueries = tableQueries
								.getAllQueries();
						if (allQueries != null) {
							this.map.putAll(allQueries);
						}
					} catch (final DatabaseInitializationException e) {
						e.printStackTrace();
					}
				}
			}

			final File dbConfig = new File(classLoader.getResource(
					"database/config/").getFile());

			if ((dbConfig != null) && dbConfig.isDirectory()) {

				final File[] configFiles = dbConfig.listFiles();

				for (final File file : configFiles) {
					data = IOUtils.toString(new FileInputStream(file));

					final XStream xstream = new XStream();
					xstream.processAnnotations(DBConfiguration.class);

					this.dbConfiguration = (DBConfiguration) xstream
							.fromXML(data);

				}

			}

			return true;

		} catch (final IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
