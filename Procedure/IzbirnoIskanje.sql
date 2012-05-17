USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[IzbirnoIskanje]    Script Date: 05/17/2012 23:08:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[IzbirnoIskanje]
(
	@avtor nvarchar(500),
	@naslov nvarchar(500),
	@leto int,
	@jezik nvarchar(100),
	@vrsta nvarchar(200)
)
AS
BEGIN
	
	DECLARE @sql nvarchar(MAX),
			@paramlist nvarchar(4000)
	SET @sql = 'SELECT	upo133.KnjigaKnjiznica.ID, upo133.Avtor.Avtor, upo133.Knjiga.Naslov, upo133.Gradivo.Vrsta, 
			upo133.Jezik.Jezik, upo133.Knjiga.LetoIzdaje, upo133.Dostop.Dostopnost, 
			upo133.KnjigaKnjiznica.StIzposojenih
	FROM    upo133.KnjigaKnjiznica INNER JOIN
            upo133.Knjiga ON upo133.KnjigaKnjiznica.KnjigaID = upo133.Knjiga.ID INNER JOIN
            upo133.Jezik ON upo133.Knjiga.JezikID = upo133.Jezik.ID INNER JOIN
            upo133.Gradivo ON upo133.Knjiga.GradivoID = upo133.Gradivo.ID INNER JOIN
            upo133.Dostop ON upo133.KnjigaKnjiznica.DostopnostID = upo133.Dostop.ID INNER JOIN
            upo133.Avtor ON upo133.Knjiga.AvtorID = upo133.Avtor.ID
			WHERE 1 = 1'
	IF(@avtor IS NOT NULL) 
		SELECT @sql = @sql + ' AND upo133.Avtor.Avtor LIKE ''%'' + @avtor + ''%'''
	IF(@naslov IS NOT NULL)
		SELECT @sql = @sql + ' AND upo133.Knjiga.Naslov LIKE ''%'' + @naslov + ''%'''
	IF(@leto IS NOT NULL)
		SELECT @sql = @sql + ' AND upo133.Knjiga.LetoIzdaje = @leto'
	IF(@jezik IS NOT NULL)
		SELECT @sql = @sql + ' AND	upo133.Jezik.Jezik = @jezik'
	IF(@vrsta IS NOT NULL)
		SELECT @sql = @sql + ' AND	upo133.Gradivo.Vrsta = @vrsta'

	SELECT @paramlist = '@avtor nvarchar(500),
						@naslov nvarchar(500),
						@leto int,
						@jezik nvarchar(100),
						@vrsta nvarchar(200)'

	EXEC sp_executesql @sql, @paramlist,
					   @avtor, @naslov, @leto, @jezik, @vrsta
	RETURN
END

GO

