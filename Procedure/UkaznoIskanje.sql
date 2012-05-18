USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[UkaznoIskanje]    Script Date: 05/17/2012 23:09:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[UkaznoIskanje]
(
	@celotniUkaz nvarchar(1000)
)
AS
	
	DECLARE @sql nvarchar(MAX)

	SET @sql = 'SELECT	upo133.KnjigaKnjiznica.ID, upo133.Avtor.Avtor, upo133.Knjiga.Naslov, upo133.Gradivo.Vrsta, 
			upo133.Jezik.Jezik, upo133.Knjiga.LetoIzdaje, upo133.Dostop.Dostopnost, 
			upo133.KnjigaKnjiznica.StIzposojenih
	FROM    upo133.KnjigaKnjiznica INNER JOIN
            upo133.Knjiga ON upo133.KnjigaKnjiznica.KnjigaID = upo133.Knjiga.ID INNER JOIN
            upo133.Jezik ON upo133.Knjiga.JezikID = upo133.Jezik.ID INNER JOIN
            upo133.Gradivo ON upo133.Knjiga.GradivoID = upo133.Gradivo.ID INNER JOIN
            upo133.Dostop ON upo133.KnjigaKnjiznica.DostopnostID = upo133.Dostop.ID INNER JOIN
            upo133.Avtor ON upo133.Knjiga.AvtorID = upo133.Avtor.ID
			WHERE ' + @celotniUkaz


	EXEC sp_executesql @sql

	RETURN

GO

