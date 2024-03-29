USE [master]
GO
/****** Object:  Database [pregledapp]    Script Date: 2/3/2020 10:20:04 PM ******/
CREATE DATABASE [pregledapp]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'pregledapp', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\pregledapp.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'pregledapp_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\pregledapp_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [pregledapp] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [pregledapp].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [pregledapp] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [pregledapp] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [pregledapp] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [pregledapp] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [pregledapp] SET ARITHABORT OFF 
GO
ALTER DATABASE [pregledapp] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [pregledapp] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [pregledapp] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [pregledapp] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [pregledapp] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [pregledapp] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [pregledapp] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [pregledapp] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [pregledapp] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [pregledapp] SET  DISABLE_BROKER 
GO
ALTER DATABASE [pregledapp] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [pregledapp] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [pregledapp] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [pregledapp] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [pregledapp] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [pregledapp] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [pregledapp] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [pregledapp] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [pregledapp] SET  MULTI_USER 
GO
ALTER DATABASE [pregledapp] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [pregledapp] SET DB_CHAINING OFF 
GO
ALTER DATABASE [pregledapp] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [pregledapp] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [pregledapp] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [pregledapp] SET QUERY_STORE = OFF
GO
USE [pregledapp]
GO
/****** Object:  User [apz-user]    Script Date: 2/3/2020 10:20:04 PM ******/
CREATE USER [apz-user] FOR LOGIN [apz-user] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [apz-user]
GO
/****** Object:  Table [dbo].[doctor]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[doctor](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[name] [varchar](255) NULL,
	[surname] [varchar](255) NULL,
	[specialization_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exam]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exam](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[date] [datetime2](7) NULL,
	[details_of_pregled] [varchar](255) NULL,
	[komentar] [varchar](255) NULL,
	[simptomi] [varchar](255) NULL,
	[doctor_id] [bigint] NOT NULL,
	[pacient_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[medical_procedure]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medical_procedure](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[description] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[medical_procedure_specialization]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medical_procedure_specialization](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[procedure_id] [bigint] NULL,
	[specialization_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[patient]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[address] [varchar](255) NULL,
	[date_of_birth] [datetime2](7) NULL,
	[name] [varchar](255) NULL,
	[social_sec_num] [varchar](255) NULL,
	[surname] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[specialization]    Script Date: 2/3/2020 10:20:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[specialization](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_on] [datetime2](7) NULL,
	[modified_on] [datetime2](7) NULL,
	[description] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_24wvv6ncorx28f58jspbxj40] UNIQUE NONCLUSTERED 
(
	[description] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[doctor]  WITH CHECK ADD  CONSTRAINT [FKq23vqgpxphxpr1wwn10fxifhh] FOREIGN KEY([specialization_id])
REFERENCES [dbo].[specialization] ([id])
GO
ALTER TABLE [dbo].[doctor] CHECK CONSTRAINT [FKq23vqgpxphxpr1wwn10fxifhh]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD  CONSTRAINT [FK6t6bb7nptubu177n2xjfi1nyp] FOREIGN KEY([doctor_id])
REFERENCES [dbo].[doctor] ([id])
GO
ALTER TABLE [dbo].[exam] CHECK CONSTRAINT [FK6t6bb7nptubu177n2xjfi1nyp]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD  CONSTRAINT [FKatqrtojd9uonnhxbgu23mb9rl] FOREIGN KEY([pacient_id])
REFERENCES [dbo].[patient] ([id])
GO
ALTER TABLE [dbo].[exam] CHECK CONSTRAINT [FKatqrtojd9uonnhxbgu23mb9rl]
GO
ALTER TABLE [dbo].[medical_procedure_specialization]  WITH CHECK ADD  CONSTRAINT [FKidx8i6w13hp5t722ihahggm29] FOREIGN KEY([specialization_id])
REFERENCES [dbo].[specialization] ([id])
GO
ALTER TABLE [dbo].[medical_procedure_specialization] CHECK CONSTRAINT [FKidx8i6w13hp5t722ihahggm29]
GO
ALTER TABLE [dbo].[medical_procedure_specialization]  WITH CHECK ADD  CONSTRAINT [FKneqab9hgbusrptsiehkiyyh3l] FOREIGN KEY([procedure_id])
REFERENCES [dbo].[medical_procedure] ([id])
GO
ALTER TABLE [dbo].[medical_procedure_specialization] CHECK CONSTRAINT [FKneqab9hgbusrptsiehkiyyh3l]
GO
USE [master]
GO
ALTER DATABASE [pregledapp] SET  READ_WRITE 
GO
