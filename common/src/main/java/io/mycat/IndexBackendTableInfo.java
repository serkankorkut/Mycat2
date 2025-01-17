/**
 * Copyright (C) <2021>  <chen junwen>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package io.mycat;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Junwen Chen
 **/
@Data
@EqualsAndHashCode
@ToString
public class IndexBackendTableInfo implements Partition {
    private String targetName;
    private SchemaInfo schemaInfo;
    public int dbIndex;
    public int tableIndex;
    public int index;

    public IndexBackendTableInfo(String targetName,
                                 String targetSchema,
                                 String targetTable,
                                 int dbIndex,
                                int tableIndex,int index) {
        this(targetName, new SchemaInfo(targetSchema, targetTable));
        this.dbIndex = dbIndex;
        if (dbIndex>0){
            System.out.println();
        }
        this.tableIndex = tableIndex;
        this.index = index;
    }

    public IndexBackendTableInfo(String targetName, SchemaInfo schemaInfo) {
        this.targetName = targetName;
        this.schemaInfo = schemaInfo;
    }

    public String getUniqueName() {
        return targetName + "." + schemaInfo.targetSchemaTable();
    }

    @Override
    public String getSchema() {
        return schemaInfo.getTargetSchema();
    }

    @Override
    public String getTable() {
        return schemaInfo.getTargetTable();
    }

    @Override
    public Integer getDbIndex() {
        return dbIndex;
    }

    @Override
    public Integer getTableIndex() {
        return tableIndex;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public String getTargetSchemaTable() {
        return schemaInfo.targetSchemaTable();
    }

    @Override
    public String toString() {
        return "{" +
                "targetName='" + getTargetName() + '\'' +
                ", schemaName='" + getSchema() + '\'' +
                ", tableName='" + getTable() + '\'' +
                ", dbIndex='" + getDbIndex() + '\'' +
                ", tableIndex='" + getTableIndex() + '\'' +
                ", index='" + getIndex() + '\'' +
                '}';
    }
}
