USE [BookMe]
GO

/****** Object:  StoredProcedure [dbo].[BrisiKnjigo]    Script Date: 04/05/2012 23:08:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[BrisiKnjigo]
	(
	@id int
	)
AS
	DELETE FROM Knjiga WHERE ID=@id
	RETURN

GO

