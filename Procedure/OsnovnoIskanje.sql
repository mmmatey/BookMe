USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[OsnovnoIskanje]    Script Date: 05/17/2012 23:09:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[OsnovnoIskanje]
(
	@vrsta nvarchar(200),
	@jezik nvarchar(100),
	@iskalniNiz nvarchar(500)
)
AS
BEGIN
	
	DECLARE @errId int
	SET @errId = 0
	
	DECLARE @sql nvarchar(MAX),
			@paramlist nvarchar(4000),
			@sql2 nvarchar(MAX),
			@paramlist2 nvarchar(4000)

	SET @sql = N'SELECT	upo133.KnjigaKnjiznica.ID, upo133.Avtor.Avtor, upo133.Knjiga.Naslov, upo133.Gradivo.Vrsta, 
			upo133.Jezik.Jezik, upo133.Knjiga.LetoIzdaje, upo133.Dostop.Dostopnost, 
			upo133.KnjigaKnjiznica.StIzposojenih INTO TAvtor
	FROM    upo133.KnjigaKnjiznica INNER JOIN
            upo133.Knjiga ON upo133.KnjigaKnjiznica.KnjigaID = upo133.Knjiga.ID INNER JOIN
            upo133.Jezik ON upo133.Knjiga.JezikID = upo133.Jezik.ID INNER JOIN
            upo133.Gradivo ON upo133.Knjiga.GradivoID = upo133.Gradivo.ID INNER JOIN
            upo133.Dostop ON upo133.KnjigaKnjiznica.DostopnostID = upo133.Dostop.ID INNER JOIN
            upo133.Avtor ON upo133.Knjiga.AvtorID = upo133.Avtor.ID
	WHERE	upo133.Avtor.Avtor LIKE ''%'' + @iskalniNiz + ''%'''
	
	IF(@vrsta IS NOT NULL)
		SELECT @sql = @sql + ' AND upo133.Gradivo.Vrsta = N'''' + @vrsta + '''''	
	
	IF(@jezik IS NOT NULL)
		SELECT @sql = @sql + ' AND upo133.Jezik.Jezik = N'''' + @jezik + '''''

	SELECT @paramlist = N'@iskalniNiz nvarchar(500),
						 @vrsta nvarchar(200),
						 @jezik nvarchar(100)'


	SET @sql2 = N'SELECT	upo133.KnjigaKnjiznica.ID, upo133.Avtor.Avtor, upo133.Knjiga.Naslov, upo133.Gradivo.Vrsta, 
			upo133.Jezik.Jezik, upo133.Knjiga.LetoIzdaje, upo133.Dostop.Dostopnost, 
			upo133.KnjigaKnjiznica.StIzposojenih INTO TNaslov
	FROM    upo133.KnjigaKnjiznica INNER JOIN
            upo133.Knjiga ON upo133.KnjigaKnjiznica.KnjigaID = upo133.Knjiga.ID INNER JOIN
            upo133.Jezik ON upo133.Knjiga.JezikID = upo133.Jezik.ID INNER JOIN
            upo133.Gradivo ON upo133.Knjiga.GradivoID = upo133.Gradivo.ID INNER JOIN
            upo133.Dostop ON upo133.KnjigaKnjiznica.DostopnostID = upo133.Dostop.ID INNER JOIN
            upo133.Avtor ON upo133.Knjiga.AvtorID = upo133.Avtor.ID
	WHERE	upo133.Knjiga.Naslov LIKE ''%'' + @iskalniNiz + ''%'''
	
	IF(@vrsta IS NOT NULL)
		SELECT @sql2 = @sql2 + ' AND upo133.Gradivo.Vrsta = N'''' + @vrsta + '''''	
	
	IF(@jezik IS NOT NULL)
		SELECT @sql2 = @sql2 + ' AND upo133.Jezik.Jezik = N'''' + @jezik + '''''

	SELECT @paramlist2 = N'@iskalniNiz nvarchar(500),
						 @vrsta nvarchar(200),
						 @jezik nvarchar(100)'

	EXEC sp_executesql @sql, @paramlist, @iskalniNiz, @vrsta, @jezik
	EXEC sp_executesql @sql2, @paramlist2, @iskalniNiz, @vrsta, @jezik

	SELECT *
	FROM TNaslov
	UNION
	SELECT *
	FROM TAvtor
	
	DROP TABLE TAvtor
	
	DROP TABLE TNaslov

	RETURN
END
GO

