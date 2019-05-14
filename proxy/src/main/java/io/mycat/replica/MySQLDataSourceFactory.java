package io.mycat.replica;

import io.mycat.config.datasource.DatasourceConfig;

/**
 * @author jamie12221
 * @date 2019-05-14 19:31
 **/
public interface MySQLDataSourceFactory {

  MySQLDatasource get(int index, DatasourceConfig datasourceConfig,
      MySQLReplica replica);
}
