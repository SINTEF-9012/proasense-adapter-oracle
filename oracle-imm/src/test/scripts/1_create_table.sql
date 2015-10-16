CREATE TABLE TNT_0000002015092323 (
  "CAPTURE_TS" TIMESTAMP(3), 
	"CAPTURE_TOFF" NUMBER(*,0), 
	"REFERENCE_ID" NVARCHAR2(50), 
	"OBJECT_ID" NVARCHAR2(50), 
	"VALUE_CHAR" NVARCHAR2(50), 
	"VALUE_INTEGER" NUMBER(*,0), 
	"VALUE_DECIMAL" NUMBER(18,6), 
	"VALUE_DATETIME" TIMESTAMP(3)
   )
  SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 262144 NEXT 2097152 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
;
