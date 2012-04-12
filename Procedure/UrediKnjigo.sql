USE [BookMe]
GO

/****** Object:  StoredProcedure [dbo].[UrediKnjigo]    Script Date: 04/05/2012 23:08:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[UrediKnjigo]
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
	declare @ID int
	SELECT @ID=ID FROM Knjiga Where Naslov Like '%'+@naslov+'%'

	SELECT @idJezika=ID FROM Jezik Where Jezik Like '%'+@jezik+'%'

	SELECT @idAvtorja=ID FROM Avtor Where Avtor Like '%'+@avtor+'%'

	SELECT @idGradiva=ID FROM Gradivo Where Vrsta Like '%'+@gradivo+'%'

	UPDATE Knjiga
	SET Naslov=@naslov , AvtorID=@idAvtorja, JezikID=@idJezika, LetoIzdaje=@leto,
	GradivoID=@idGradiva, DostopnostZaloge=@dostopno
	WHERE ID=@ID
	RETURN

GO

