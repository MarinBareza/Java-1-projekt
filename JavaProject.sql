CREATE DATABASE Java1
GO

USE Java1
GO

--RESET DATABASE
CREATE PROC ClearDatabase
AS
DELETE FROM Actor
DELETE FROM Director
DELETE FROM GenreMovie
DELETE FROM Genre
DELETE FROM Person
DELETE FROM Movie
DELETE FROM [User]

DROP TABLE Actor
DROP TABLE Director
DROP TABLE GenreMovie
DROP TABLE Movie
DROP TABLE Person
DROP TABLE Genre
DROP TABLE [User]

CREATE TABLE [User](
	IDUser INT PRIMARY KEY IDENTITY NOT NULL,
	Username NVARCHAR(50) NOT NULL UNIQUE,
	[Password] INT NOT NULL,
	Administrator BIT NOT NULL
)

CREATE TABLE Movie (
	IDMovie INT PRIMARY KEY IDENTITY NOT NULL,
	Title NVARCHAR(50) NOT NULL,
	DatePublished nvarchar(50) NOT NULL,
	[Description] NVARCHAR(MAX),
	OriginalTitle NVARCHAR(50) NOT NULL UNIQUE,
	Duration INT NOT NULL,
	PicturePath NVARCHAR(MAX) NOT NULL,
	Link NVARCHAR(MAX) NOT NULL
)

CREATE TABLE Person (
	IDPerson INT PRIMARY KEY IDENTITY NOT NULL,
	FirstName NVARCHAR(50) NOT NULL,
	LastName NVARCHAR(50) NOT NULL
)

CREATE TABLE Genre (
	IDGenre INT PRIMARY KEY IDENTITY NOT NULL,
	[Name] NVARCHAR(50) NOT NULL UNIQUE
)

CREATE TABLE GenreMovie (
	IDGenreMovie INT PRIMARY KEY IDENTITY NOT NULL,
	GenreID INT NOT NULL FOREIGN KEY REFERENCES Genre(IDGenre),
	MovieID INT NOT NULL FOREIGN KEY REFERENCES Movie(IDMovie)
)

CREATE TABLE Actor (
	IDActor INT PRIMARY KEY IDENTITY NOT NULL,
	PersonID INT NOT NULL FOREIGN KEY REFERENCES Person(IDPerson),
	MovieID INT NOT NULL FOREIGN KEY REFERENCES Movie(IDMovie)
)

CREATE TABLE Director (
	IDDirector INT PRIMARY KEY IDENTITY NOT NULL,
	PersonID INT NOT NULL FOREIGN KEY REFERENCES Person(IDPerson),
	MovieID INT NOT NULL FOREIGN KEY REFERENCES Movie(IDMovie)
)
GO

CREATE PROC CreateAdminOnInitialization 
	@ID INT OUTPUT
AS 
INSERT INTO [User] VALUES ('admin', 1518351236, 1)
SET @ID = SCOPE_IDENTITY()
GO

CREATE PROC CreateUser
	@USERNAME NVARCHAR(50),
	@PASSWORD INT,
	@ADMINISTRATOR BIT,
	@Return INT OUTPUT
AS
SET @Return = 1
IF NOT EXISTS (SELECT * FROM [User] WHERE Username = @USERNAME)
	BEGIN
		INSERT INTO [User] VALUES (@USERNAME, @PASSWORD, @ADMINISTRATOR)
		SET @Return = 0
	END
GO

CREATE PROC UpdateUser
	@ID INT,
	@USERNAME NVARCHAR(50),
	@PASSWORD INT,
	@ADMINISTRATOR BIT
AS
UPDATE [User] 
SET Username = @USERNAME, [Password] = @PASSWORD, Administrator = @ADMINISTRATOR
WHERE @ID = IDUser
GO

CREATE PROC DeleteUser
	@Username NVARCHAR(50),
	@Return INT OUTPUT
AS
SET @Return = 1
IF EXISTS (SELECT * FROM [User] WHERE Username = @Username)
	BEGIN
		DELETE FROM [User] WHERE Username = @Username
		SET @Return = 0
	END
GO

CREATE PROC SelectUser
	@ID INT
AS
SELECT * FROM [User] WHERE @ID =IDUser
GO

CREATE PROC SelectUsers 
AS SELECT * FROM [User] 
GO

CREATE PROC SelectUserByUsername
	@USERNAME NVARCHAR(50)
AS
SELECT * FROM [User] WHERE Username = @USERNAME
GO

--MOVIE
CREATE PROC CreateMovie
	@Title NVARCHAR(50),
	@DatePublished NVARCHAR(50),
	@Description NVARCHAR(MAX),
	@OriginalTitle NVARCHAR(50),
	@Duration INT,
	@PicturePath NVARCHAR(MAX),
	@Link NVARCHAR(MAX),
	@ID INT OUTPUT
AS
IF NOT EXISTS (SELECT * FROM Movie WHERE OriginalTitle = @OriginalTitle)
	BEGIN
		INSERT INTO Movie VALUES (@Title, @DatePublished, @Description, @OriginalTitle, @Duration, @PicturePath, @Link)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROC UpdateMovie
	@ID INT,
	@Title NVARCHAR(50),
	@DatePublished NVARCHAR(50),
	@Description NVARCHAR(MAX),
	@OriginalTitle NVARCHAR(50),
	@Duration INT,
	@PicturePath NVARCHAR(MAX),
	@Link NVARCHAR(MAX)
AS
UPDATE Movie 
SET Title = @Title, DatePublished = @DatePublished, [Description] = @Description, OriginalTitle= @OriginalTitle, Duration = @Duration, PicturePath = @PicturePath, Link = @Link
WHERE IDMovie = @ID
GO

CREATE PROC DeleteMovie
	@ID INT
AS
DELETE FROM Actor WHERE MovieID = @ID
DELETE FROM Director WHERE MovieID = @ID
DELETE FROM GenreMovie WHERE MovieID = @ID
DELETE FROM Movie WHERE IDMovie = @ID
GO

CREATE PROC SelectMovie
	@ID INT
AS
SELECT * FROM Movie WHERE IDMovie = @ID
GO

CREATE PROC SelectMovies 
AS SELECT * FROM Movie 
GO

--PERSON
CREATE PROC CreatePerson
	@FirstName NVARCHAR(50),
	@LastName NVARCHAR(50),
	@ID INT OUTPUT
AS
IF NOT EXISTS (SELECT * FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName)
	BEGIN
		INSERT INTO Person VALUES (@FirstName, @LastName)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROC UpdatePerson
	@ID INT,
	@FirstName NVARCHAR(25),
	@LastName NVARCHAR(25)
AS
UPDATE Person 
SET FirstName = @FirstName, LastName = @LastName
WHERE IDPerson = @ID
GO

CREATE PROC DeletePerson
	@ID INT,
	@Return INT OUTPUT
AS
SET @Return = 1
IF NOT EXISTS (SELECT * FROM Actor a WHERE a.PersonID = @ID) AND NOT EXISTS (SELECT * FROM Director d WHERE d.PersonID = @ID)
	BEGIN
		DELETE FROM Person WHERE IDPerson = @ID
		SET @Return = 0
	END
GO

CREATE PROC SelectPerson
	@FirstName NVARCHAR(50),
	@LastName NVARCHAR(50),
	@ID INT OUTPUT
AS
IF EXISTS (SELECT * FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName)
	BEGIN
		SELECT @ID = p.IDPerson FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName
	END
GO

CREATE PROC SelectPeople
AS SELECT * FROM Person 
GO

--GENRE
CREATE PROC CreateGenre
	@MovieID INT,
	@Name NVARCHAR(50),
	@ID INT OUTPUT
AS
DECLARE @GenreID INT
IF EXISTS (SELECT * FROM Genre g WHERE g.[Name] = @Name)
	BEGIN
		SELECT @GenreID = g.IDGenre FROM Genre g WHERE g.[Name] = @Name
		IF NOT EXISTS (SELECT * FROM GenreMovie gm WHERE gm.GenreID = @GenreID AND gm.MovieID = @MovieID)
			BEGIN
				INSERT INTO GenreMovie (GenreID, MovieID) VALUES (@GenreID, @MovieID)
				SET @ID = SCOPE_IDENTITY()
			END
	END
ELSE
	BEGIN
		INSERT INTO Genre VALUES (@Name)
		SET @GenreID = SCOPE_IDENTITY()
		INSERT INTO GenreMovie (GenreID, MovieID) VALUES (@GenreID, @MovieID)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROC UpdateGenre
	@ID INT,
	@Name NVARCHAR(25)
AS
UPDATE Genre 
SET Name = @Name
WHERE IDGenre = @ID
GO

CREATE PROC DeleteGenre
	@ID INT,
	@Return INT OUTPUT
AS
SET @Return = 1
IF NOT EXISTS (SELECT * FROM GenreMovie gm WHERE gm.GenreID = @ID)
	BEGIN
		DELETE FROM Genre WHERE IDGenre = @ID
		SET @Return = 0
	END
GO

CREATE PROC SelectGenre
	@Name NVARCHAR(50),
	@ID INT OUTPUT
AS
IF EXISTS (SELECT * FROM Genre WHERE [Name] = @Name)
	BEGIN
		SELECT @ID = IDGenre FROM Genre WHERE [Name] = @Name
	END
GO

CREATE PROC SelectGenres
AS SELECT * FROM Genre 
GO

--ACTOR
CREATE PROC CreateActor
	@MovieID INT,
	@FirstName NVARCHAR(50),
	@LastName NVARCHAR(50),
	@ID INT OUTPUT
AS
DECLARE @PersonID INT
IF EXISTS (SELECT * FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName)
	BEGIN
		SELECT @PersonID = p.IDPerson FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName
		IF NOT EXISTS(SELECT * FROM Actor a WHERE a.MovieID = @MovieID AND a.PersonID = @PersonID)
			BEGIN
				INSERT INTO Actor (PersonID, MovieID) VALUES (@PersonID, @MovieID)
				SET @ID = SCOPE_IDENTITY()
			END
	END
ELSE
	BEGIN
		EXEC CreatePerson @FirstName, @LastName, @PersonID OUTPUT
		INSERT INTO Actor (PersonID, MovieID) VALUES (@PersonID, @MovieID)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROC AddActorToMovie
	@PersonID INT,
	@MovieID INT
	--@ID INT OUTPUT
AS
INSERT INTO Actor (PersonID, MovieID) VALUES (@PersonID, @MovieID)
--SET @ID = SCOPE_IDENTITY()
GO

CREATE PROC RemoveActorFromMovie
	@PersonID INT,
	@MovieID INT
AS
DELETE FROM Actor WHERE PersonID = @PersonID AND MovieID = @MovieID
GO

CREATE PROC GetMoviesFromActor
	@IDActor INT
AS
SELECT * 
FROM Movie m
INNER JOIN Actor a ON a.MovieID = m.IDMovie
INNER JOIN Person p ON p.IDPerson = a.PersonID
WHERE p.IDPerson = @IDActor
GO

CREATE PROC SelectActorsFromMovie
	@IDMovie INT
AS
SELECT p.IDPerson, p.FirstName, p.LastName
FROM Actor a
INNER JOIN Person p ON p.IDPerson = a.PersonID
WHERE a.MovieID = @IDMovie
GO

--DIRECTOR
CREATE PROC CreateDirector
	@MovieID INT,
	@FirstName NVARCHAR(50),
	@LastName NVARCHAR(50),
	@ID INT OUTPUT
AS
DECLARE @PersonID INT
IF EXISTS (SELECT * FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName)
	BEGIN
		SELECT @PersonID = p.IDPerson FROM Person p WHERE p.FirstName = @FirstName AND p.LastName = @LastName
		IF NOT EXISTS(SELECT * FROM Director d WHERE d.MovieID = @MovieID AND d.PersonID = @PersonID)
			BEGIN
				INSERT INTO Director (PersonID, MovieID) VALUES (@PersonID, @MovieID)
				SET @ID = SCOPE_IDENTITY()
			END
	END
ELSE
	BEGIN
		EXEC CreatePerson @FirstName, @LastName, @PersonID OUTPUT
		INSERT INTO Director(PersonID, MovieID) VALUES (@PersonID, @MovieID)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROC AddDirectorToMovie
	@PersonID INT,
	@MovieID INT
AS
INSERT INTO Director(PersonID, MovieID) VALUES (@PersonID, @MovieID)
GO

CREATE PROC RemoveDirectorFromMovie
	@PersonID INT,
	@MovieID INT
AS
DELETE FROM Director WHERE PersonID = @PersonID AND MovieID = @MovieID
GO

CREATE PROC GetMoviesFromDirector
	@IDDirector INT
AS
SELECT * 
FROM Movie m
INNER JOIN Director d ON d.MovieID = m.IDMovie
INNER JOIN Person p ON p.IDPerson = d.PersonID
WHERE p.IDPerson = @IDDirector
GO

CREATE PROC SelectDirectorsFromMovie
	@IDMovie INT
AS
SELECT p.IDPerson, p.FirstName, p.LastName
FROM Director d
INNER JOIN Person p ON p.IDPerson = d.PersonID
WHERE d.MovieID = @IDMovie
GO

--GENREMOVIE
CREATE PROC AddGenreToMovie
	@GenreID INT,
	@MovieID INT
AS
INSERT INTO GenreMovie(GenreID, MovieID) VALUES (@GenreID, @MovieID)
GO

CREATE PROC RemoveGenreFromMovie
	@GenreID INT,
	@MovieID INT
AS
DELETE FROM GenreMovie WHERE GenreID = @GenreID AND MovieID = @MovieID
GO

CREATE PROC GetMoviesFromGenre
	@IDGenre INT
AS
SELECT * 
FROM Movie m
INNER JOIN GenreMovie gm ON gm.MovieID = m.IDMovie
INNER JOIN Genre g ON g.IDGenre = gm.GenreID
WHERE g.IDGenre = @IDGenre
GO

CREATE PROC SelectGenresFromMovie
	@IDMovie INT
AS
SELECT * 
FROM Genre g
INNER JOIN GenreMovie gm ON gm.GenreID = g.IDGenre
INNER JOIN Movie m ON m.IDMovie = gm.MovieID
WHERE m.IDMovie = @IDMovie
GO