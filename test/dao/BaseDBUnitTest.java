package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * DBUnitを介して単体テストを実施するテストクラスが継承する基底DBUnitテストクラス
 * @author tutor
 */
public class BaseDBUnitTest {

	public static final String DIR_FIXTURES = "test/resources/fixtures";

	/**
	 * DBUit関連オブジェクト
	 * jdbcConnection   JDBCコネクション
	 * dbunitConnection DBUnit用データベース接続オブジェクト
	 */
	private Connection jdbcConnection = null;
	private IDatabaseConnection dbunitConnection = null;
	private IDataSet dataset = null;

	/**
	 * DBUnit関連オブジェクトを取得し、データベースにデータセットを登録する。
	 * @param datasetPath 登録するデータセット用XMLファイルパス
	 * @throws Exception
	 */
	protected void createDBUnitConnections(String datasetPath) throws Exception {
		// JDBCコネクションを取得
		this.jdbcConnection = TestDbUtils.getConnection();
		// DBUnit用コネクションを取得
		this.dbunitConnection = new DatabaseConnection(jdbcConnection);
		// データセットを取得
		FileInputStream inputStream = new FileInputStream(datasetPath);
		FlatXmlDataSetBuilder flatXmlBuilder = new FlatXmlDataSetBuilder();
		this.dataset = flatXmlBuilder.build(inputStream);
		// データセットをセットアップ
		DatabaseOperation.CLEAN_INSERT.execute(this.dbunitConnection, this.dataset);
	}

	/**
	 * データベースに登録したデータセットを削除し、DBUnit関連オブジェクトを破棄する。
	 * @throws Exception
	 */
	protected void shutdownDBUnitConnection() throws Exception {
		// データセットを破棄
		DatabaseOperation.DELETE_ALL.execute(this.dbunitConnection, this.dataset);
		// データセットの破棄
		if (this.dataset != null) this.dataset = null;
		// DBunit用コネクションの破棄
		if (this.dbunitConnection != null) this.dbunitConnection.close();
		// JDBCコネクションの破棄
		if (this.jdbcConnection != null) this.jdbcConnection.close();
	}

	/**
	 * 更新系レコード操作の実行後に期待されるテーブルの状態を生成する。
	 * @param targetFilepath 期待値を定義しているXMLファイルパス
	 * @param targetTableName 期待値の対象テーブル名
	 * @return 指定されたテーブルの期待値
	 * @throws Exception
	 */
	public ITable createExpectedTable(String targetFilepath, String targetTableName) throws Exception {
		File expectedXmlFile = new File(targetFilepath);
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet expectedDataset = builder.build(expectedXmlFile);
		ITable expected = expectedDataset.getTable(targetTableName);
		return expected;
	}

	/**
	 * 更新系レコード操作の実行後の実際のテーブルの状態を生成する。
	 * @param targettableName 操作対象テーブル名
	 * @param excludedColumns 対象テーブルのうち比較に使用しない項目のリスト
	 * @return 指定されたテーブルの実行値
	 * @throws Exception
	 */
	public ITable createEctualTable(String targetTableName, String[] excludedColumns) throws Exception {
		IDataSet idataset = this.dbunitConnection.createDataSet();
		ITable actual = idataset.getTable(targetTableName);
		actual = DefaultColumnFilter.excludedColumnsTable(actual, excludedColumns);
		return actual;
	}


}
