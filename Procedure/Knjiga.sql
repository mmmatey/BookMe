USE [BookMe]
GO

/****** Object:  Table [dbo].[Knjiga]    Script Date: 04/05/2012 23:10:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Knjiga](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Naslov] [nvarchar](500) NULL,
	[AvtorID] [int] NULL,
	[JezikID] [int] NULL,
	[LetoIzdaje] [int] NULL,
	[GradivoID] [int] NULL,
	[DostopnostZaloge] [nvarchar](100) NULL,
 CONSTRAINT [PK_Knjiga] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[Knjiga]  WITH CHECK ADD  CONSTRAINT [FK_Knjiga_Avtor] FOREIGN KEY([AvtorID])
REFERENCES [dbo].[Avtor] ([ID])
GO

ALTER TABLE [dbo].[Knjiga] CHECK CONSTRAINT [FK_Knjiga_Avtor]
GO

ALTER TABLE [dbo].[Knjiga]  WITH CHECK ADD  CONSTRAINT [FK_Knjiga_Gradivo] FOREIGN KEY([GradivoID])
REFERENCES [dbo].[Gradivo] ([ID])
GO

ALTER TABLE [dbo].[Knjiga] CHECK CONSTRAINT [FK_Knjiga_Gradivo]
GO

ALTER TABLE [dbo].[Knjiga]  WITH CHECK ADD  CONSTRAINT [FK_Knjiga_Jezik] FOREIGN KEY([JezikID])
REFERENCES [dbo].[Jezik] ([ID])
GO

ALTER TABLE [dbo].[Knjiga] CHECK CONSTRAINT [FK_Knjiga_Jezik]
GO

