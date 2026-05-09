package com.example.mobileprogrammingproject.model

import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.presentation.data.Song

// list for dynamically loading the playlists in PlaylistDetailsScreen
val playlistsHardcoded = listOf(

    Playlist(
        1,
        "Workout mix",
        listOf(
            Song(1001, "Eye of the tiger", "John Doe", "2:51"),
            Song(1002, "ZITTI E BUONI", "Maneskin", "3:19")
        ),
        "7:10"
    ),

    Playlist(
        2,
        "Pop",
        listOf(
            Song(1003, "Eye of the tiger", "John Doe", "2:51"),
            Song(1004, "ZITTI E BUONI", "Maneskin", "3:19")
        ),
        "12:20"
    ),

    Playlist(
        3,
        "Rock",
        listOf(
            Song(1005, "Eye of the tiger", "John Doe", "2:51"),
            Song(1006, "ZITTI E BUONI", "Maneskin", "3:19")
        ),
        "22:31"
    ),

    Playlist(
        4,
        "Midnight Echoes",
        listOf(
            Song(1007, "Blinding Lights", "The Weeknd", "3:20"),
            Song(1008, "After Dark", "Mr. Kitty", "4:17"),
            Song(1009, "Nightcall", "Kavinsky", "4:19"),
            Song(1010, "Do I Wanna Know?", "Arctic Monkeys", "4:32")
        ),
        "16:28"
    ),

    Playlist(
        5,
        "Velvet Vibes",
        listOf(
            Song(1011, "Earned It", "The Weeknd", "4:10"),
            Song(1012, "Adorn", "Miguel", "3:14"),
            Song(1013, "Redbone", "Childish Gambino", "5:26"),
            Song(1014, "Best Part", "Daniel Caesar ft. H.E.R.", "3:29")
        ),
        "16:19"
    ),

    Playlist(
        6,
        "Neon Nights",
        listOf(
            Song(1015, "Levitating", "Dua Lipa", "3:23"),
            Song(1016, "Physical", "Dua Lipa", "3:14"),
            Song(1017, "Take On Me", "a-ha", "3:46"),
            Song(1018, "Midnight City", "M83", "4:03")
        ),
        "14:26"
    ),

    Playlist(
        7,
        "Chillwave Therapy",
        listOf(
            Song(1019, "Sunset Lover", "Petit Biscuit", "3:58"),
            Song(1020, "Weightless", "Tycho", "4:38"),
            Song(1021, "A Walk", "Tycho", "5:18"),
            Song(1022, "Intro", "The xx", "2:07")
        ),
        "16:01"
    ),

    Playlist(
        8,
        "Golden Hour Grooves",
        listOf(
            Song(1023, "Golden", "Harry Styles", "3:28"),
            Song(1024, "Yellow", "Coldplay", "4:29"),
            Song(1025, "Electric Feel", "MGMT", "3:49"),
            Song(1026, "Shotgun", "George Ezra", "3:21")
        ),
        "15:07"
    ),

    Playlist(
        9,
        "Roadtrip Anthems",
        listOf(
            Song(1027, "Shut Up and Drive", "Rihanna", "3:33"),
            Song(1028, "On Top of the World", "Imagine Dragons", "3:12"),
            Song(1029, "Life is a Highway", "Rascal Flatts", "4:36"),
            Song(1030, "Send Me On My Way", "Rusted Root", "4:24")
        ),
        "15:45"
    ),

    Playlist(
        10,
        "Rainy Day Acoustics",
        listOf(
            Song(1031, "Skinny Love", "Bon Iver", "3:58"),
            Song(1032, "All I Want", "Kodaline", "5:05"),
            Song(1033, "Let Her Go", "Passenger", "4:12"),
            Song(1034, "Photograph", "Ed Sheeran", "4:19")
        ),
        "17:34"
    ),

    Playlist(
        11,
        "Bass & Bliss",
        listOf(
            Song(1035, "Titanium", "David Guetta ft. Sia", "4:05"),
            Song(1036, "Animals", "Martin Garrix", "5:03"),
            Song(1037, "Wake Me Up", "Avicii", "4:07"),
            Song(1038, "Clarity", "Zedd ft. Foxes", "4:31")
        ),
        "17:46"
    ),

    Playlist(
        12,
        "Coffeehouse Sessions",
        listOf(
            Song(1039, "Banana Pancakes", "Jack Johnson", "3:11"),
            Song(1040, "Budapest", "George Ezra", "3:20"),
            Song(1041, "Put Your Records On", "Corinne Bailey Rae", "3:35"),
            Song(1042, "Ho Hey", "The Lumineers", "2:43")
        ),
        "12:49"
    ),

    Playlist(
        13,
        "Throwback Frequencies",
        listOf(
            Song(1043, "Billie Jean", "Michael Jackson", "4:54"),
            Song(1044, "Like a Prayer", "Madonna", "5:41"),
            Song(1045, "Smells Like Teen Spirit", "Nirvana", "5:01"),
            Song(1046, "Wonderwall", "Oasis", "4:18")
        ),
        "19:54"
    ),

    Playlist(
        14,
        "Indie Daydreams",
        listOf(
            Song(1047, "Riptide", "Vance Joy", "3:24"),
            Song(1048, "Sweater Weather", "The Neighbourhood", "4:00"),
            Song(1049, "Take Me Out", "Franz Ferdinand", "3:59"),
            Song(1050, "Somebody Else", "The 1975", "5:47")
        ),
        "17:10"
    ),

    Playlist(
        15,
        "Late Night Coding",
        listOf(
            Song(1051, "Time", "Hans Zimmer", "4:35"),
            Song(1052, "Experience", "Ludovico Einaudi", "5:15"),
            Song(1053, "Daydreaming", "Instrumental", "4:12"),
            Song(1054, "Intro", "Alan Walker", "2:53")
        ),
        "16:55"
    ),

    Playlist(
        16,
        "Sunrise Serenity",
        listOf(
            Song(1055, "Here Comes The Sun", "The Beatles", "3:05"),
            Song(1056, "Better Together", "Jack Johnson", "3:27"),
            Song(1057, "Bloom", "The Paper Kites", "3:32"),
            Song(1058, "Home", "Edward Sharpe & The Magnetic Zeros", "5:06")
        ),
        "15:10"
    ),

    Playlist(
        17,
        "Heartbreak & Harmony",
        listOf(
            Song(1059, "Someone Like You", "Adele", "4:45"),
            Song(1060, "Stay", "Rihanna ft. Mikky Ekko", "4:00"),
            Song(1061, "Fix You", "Coldplay", "4:56"),
            Song(1062, "Let Me Down Slowly", "Alec Benjamin", "2:49")
        ),
        "16:30"
    ),

    Playlist(
        18,
        "Weekend Warm-Up",
        listOf(
            Song(1063, "Uptown Funk", "Mark Ronson ft. Bruno Mars", "4:30"),
            Song(1064, "Can’t Stop The Feeling!", "Justin Timberlake", "3:56"),
            Song(1065, "Don’t Start Now", "Dua Lipa", "3:03"),
            Song(1066, "Happy", "Pharrell Williams", "3:53")
        ),
        "15:22"
    )
)


// list for dynamically loading the DashboardScreen
val options = listOf<String>("Total playlists", "Total songs", "Recently played", "Quick actions")


// list of all songs that will be rendered to SongSearchScreen
val songs = listOf(
    Song(2001, "Dale", "Jala Brat", "2:17"),
    Song(2002, "Toronto", "Jala Brat & Buba Corelli", "2:08"),
    Song(2003, "Babylon", "Jala Brat & Buba Corelli", "2:24"),
    Song(2004, "Tec-9", "Jala Brat & Buba Corelli", "2:37"),
    Song(101, "Da šutiš", "Dino Merlin", "4:12"),
    Song(102, "Kad si rekla da me voliš", "Dino Merlin", "4:05"),
    Song(103, "Sve je laž", "Dino Merlin", "3:58"),
    Song(104, "Nešto lijepo treba da se desi", "Dino Merlin", "4:20"),
    Song(105, "Sanjao sam noćas da te nemam", "Dino Merlin", "4:33"),
    Song(106, "Ti i ja", "Dino Merlin", "3:47"),
    Song(107, "Ljubav nije paradajz", "Dino Merlin", "3:41"),
    Song(108, "Zauvijek moja", "Dino Merlin", "4:10"),
    Song(109, "Umri prije smrti", "Dino Merlin", "4:25"),
    Song(110, "Odlaziš", "Dino Merlin", "3:52")
)