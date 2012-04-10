USE [BookMe]
GO

/****** Object:  StoredProcedure [dbo].[DodajKnjigo]    Script Date: 04/05/2012 23:08:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[DodajKnjigo]
	(
	@naslov nvarchar(500),
	@avtor varchar(100),
	@jezik varchar(100),
	@leto int,
	@gradivo varchar(100),
	@dostopno varchar(100)
	)
AS
	declare @idJezika int
	declare @idAvtorja int
	declare @idGradiva int
	SELECT @idJezika=ID FROM Jezik Where Jezik Like '%'+@jezik+'%'

	SELECT @idAvtorja=ID FROM Avtor Where Avtor Like '%'+@avtor+'%'

	SELECT @idGradiva=ID FROM Gradivo Where Vrsta Like '%'+@gradivo+'%'

	INSERT INTO Knjiga ([Naslov]
      ,[AvtorID]
      ,[JezikID]
      ,[LetoIzdaje]
      ,[GradivoID]
      ,[DostopnostZaloge])
	  VALUES(@naslov, @idAvtorja, @idJezika, @leto, @idGradiva, @dostopno)

	RETURN

GO

