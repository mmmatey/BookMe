USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[KnjizniceSKnjigo]    Script Date: 05/24/2012 14:05:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[KnjizniceSKnjigo]
(
	@KnjigaId int
)
AS

	SELECT  dbo.Knjiznica.Naziv, dbo.Knjiznica.Kraj, dbo.Knjiznica.X, dbo.Knjiznica.Y, 
	upo133.Dostop.Dostopnost, upo133.KnjigaKnjiznica.Stevilo, upo133.KnjigaKnjiznica.StIzposojenih
	FROM    upo133.KnjigaKnjiznica INNER JOIN
            dbo.Knjiznica ON upo133.KnjigaKnjiznica.KnjiznicaID = dbo.Knjiznica.ID INNER JOIN
			upo133.Knjiga ON upo133.Knjiga.ID = upo133.KnjigaKnjiznica.KnjigaID INNER JOIN
			upo133.Dostop ON upo133.KnjigaKnjiznica.DostopnostID = upo133.Dostop.ID
	WHERE	upo133.Knjiga.ID = @KnjigaId
	AND		upo133.KnjigaKnjiznica.StIzposojenih <> upo133.KnjigaKnjiznica.Stevilo

	RETURN

GO

