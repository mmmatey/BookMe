USE [BookMe]
GO

/****** Object:  Table [dbo].[Knjiznica]    Script Date: 04/05/2012 23:10:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Knjiznica](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Naziv] [nvarchar](500) NOT NULL,
	[Kraj] [nvarchar](500) NOT NULL,
	[Akronim] [nvarchar](50) NOT NULL,
	[Singla] [int] NOT NULL,
 CONSTRAINT [PK_Knjiznica] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

