USE [BookMe]
GO

/****** Object:  StoredProcedure [upo133].[DodajUporabnika]    Script Date: 05/24/2012 14:04:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [upo133].[DodajUporabnika]
(
	@UpoIme nvarchar(256),
	@Geslo nvarchar(256)
)
AS

	IF (EXISTS (SELECT * FROM upo133.Uporabnik WHERE UpoIme = @UpoIme))
		RETURN (0)

	INSERT INTO upo133.Uporabnik
	(
		UpoIme,
		Geslo,
		RegDatum
	)
	VALUES
	(
		@UpoIme,
		@Geslo,
		GETDATE()
	)
	RETURN (1)

GO

