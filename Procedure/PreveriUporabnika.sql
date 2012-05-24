USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[PreveriUporabnika]    Script Date: 05/24/2012 14:05:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[PreveriUporabnika]
(
	@UpoIme nvarchar(256),
	@Geslo nvarchar(256)
)
AS

	IF (EXISTS (SELECT * FROM upo133.Uporabnik WHERE UpoIme = @UpoIme AND Geslo = @Geslo))
		RETURN (1)
	
	RETURN (0)

GO

