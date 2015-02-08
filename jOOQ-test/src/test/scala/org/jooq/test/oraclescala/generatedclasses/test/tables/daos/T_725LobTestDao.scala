/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.daos


import java.lang.Integer
import java.util.List

import org.jooq.Configuration
import org.jooq.impl.DAOImpl
import org.jooq.test.oraclescala.generatedclasses.test.tables.T_725LobTest
import org.jooq.test.oraclescala.generatedclasses.test.tables.records.T_725LobTestRecord

import scala.Array


/**
 * This class is generated by jOOQ.
 */
class T_725LobTestDao(configuration : Configuration) extends DAOImpl[T_725LobTestRecord, org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest, Integer](T_725LobTest.T_725_LOB_TEST, classOf[org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest], configuration) {

	/**
	 * Create a new T_725LobTestDao without any configuration
	 */
	def this() = {
		this(null)
	}

	override protected def getId(o : org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest) : Integer = {
		o.getId
	}

	/**
	 * Fetch records that have <code>ID IN (values)</code>
	 */
	def fetchById(values : Integer*) : List[org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest] = {
		fetch(T_725LobTest.T_725_LOB_TEST.ID, values:_*)
	}

	/**
	 * Fetch a unique record that has <code>ID = value</code>
	 */
	def fetchOneById(value : Integer) : org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest = {
		fetchOne(T_725LobTest.T_725_LOB_TEST.ID, value)
	}

	/**
	 * Fetch records that have <code>LOB IN (values)</code>
	 */
	def fetchByLob(values : Array[scala.Byte]*) : List[org.jooq.test.oraclescala.generatedclasses.test.tables.pojos.T_725LobTest] = {
		fetch(T_725LobTest.T_725_LOB_TEST.LOB, values:_*)
	}
}
