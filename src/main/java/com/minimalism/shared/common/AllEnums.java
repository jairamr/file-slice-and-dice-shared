package com.minimalism.shared.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class AllEnums {
    public enum FileTypes{
        BIN,
        CSV,
        TEXT
    }

    public enum RecordTypes {
        DELIMITED,
        FIXED_LENGTH,
        VARIABLE_LENGTH
    }

    public enum DataTypes {
        BOOLEAN(0),
        BIG_DECIMAL(1),
        CHARACTER(2),
        DOUBLE(3),
        FLOAT(4),
        INTEGER(5),
        LOCAL_DATE(6),
        LOCAL_TIME(7),
        LONG(8),
        STRING(9);
        
        private int dataType;
        DataTypes(int dataType) {
            this.dataType = dataType;
        }
        public String getTypeName() {
            return this.getType().getSimpleName();
        }

        public DataTypes getDataType(String typeName) {
            DataTypes returnValue = DataTypes.STRING;
            switch(typeName) {
                case "Character":
                returnValue = DataTypes.CHARACTER;
                break;
                case "Boolean":
                returnValue = DataTypes.BOOLEAN;
                break;
                case "BigDecimal":
                returnValue = DataTypes.BIG_DECIMAL;
                break;
                case "Double":
                returnValue = DataTypes.DOUBLE;
                break;
                case "Float":
                returnValue = DataTypes.FLOAT;
                break;
                case "Integer":
                returnValue = DataTypes.INTEGER;
                break;
                case "LocalDate":
                returnValue = DataTypes.LOCAL_DATE;
                break;
                case "LocalTime":
                returnValue = DataTypes.LOCAL_TIME;
                break;
                case "Long":
                returnValue = DataTypes.LONG;
                break;
                default: // default return value is STRING
                break;                
            }
            return returnValue;
        }

        public Class<?> getType() {
            Class<?> type;
            switch (this.dataType) {
                case 0:
                type = Boolean.class;
                break;
                case 1:
                type = BigDecimal.class;
                break;
                case 2:
                type = Character.class;
                break;
                case 3:
                type = Double.class;
                break;
                case 4:
                type = Float.class;
                break;
                case 5:
                type = Integer.class;
                break;
                case 6:
                type = LocalDate.class;
                break;
                case 7:
                type = LocalTime.class;
                break;
                case 8:
                type = Long.class;
                break;
                case 9:
                type = String.class;
                break;
                default:
                type = String.class;
                break;
            }
            return type;
        }
    }

    public enum Directories {
        INPUT_DATA,
        INPUT_DATA_CSV,
        INPUT_DATA_BIN,
        INPUT_DATA_DEFINITION,
        INSTRUMENTATION,
        OUTPUT_DATA,
        OUTPUT_DATA_CSV,
        OUTPUT_DATA_BIN,
        OUTPUT_DATA_DEFINITION,
        LOST_AND_FOUND,
        ARCHIVE,
        ARCHIVE_INPUT,
        ARCHIVE_INPUT_CSV,
        ARCHIVE_INPUT_BIN,
        ARCHIVE_OUTPUT,
        ARCHIVE_OUTPUT_CSV,
        ARCHIVE_OUTPUT_BIN
    }

    public enum DataSourceTypes {
        AMQP,
        DATABASE,
        FILESYSTEM,
        PUBSUB,
        RESTFUL,
        WEBSOCKET
    }

    public enum DataSources {
        ACTIVE_MQ(DataSourceTypes.AMQP),
        BROKER_J(DataSourceTypes.AMQP),
        RABBIT_MQ(DataSourceTypes.AMQP),
        KAFKA(DataSourceTypes.PUBSUB),
        NTFS(DataSourceTypes.FILESYSTEM),
        UNIX_FS(DataSourceTypes.FILESYSTEM),
        GENERIC_REST(DataSourceTypes.RESTFUL),
        SPRING_BOOT(DataSourceTypes.RESTFUL),
        GENERIC_WEBSOCKET(DataSourceTypes.WEBSOCKET),
        SPRING_MVC(DataSourceTypes.WEBSOCKET),
        ASP(DataSourceTypes.RESTFUL),
        ;

        private DataSourceTypes dataSourceType;

        DataSources(DataSourceTypes dataSourceType) {
            this.dataSourceType = dataSourceType;
        }

        public DataSourceTypes getDataSourceType() {
            return this.dataSourceType;
        }

    }

}
