all:
	export NLS_LANG=GERMAN_GERMANY.WE8ISO8859P1
	sqlplus ${OraSsdProdIdl} @start.sql

clean:
	rm -f *~

inf:
	export NLS_LANG=GERMAN_GERMANY.WE8ISO8859P1
	sqlplus ${OraSsdProdInf} @start.sql
