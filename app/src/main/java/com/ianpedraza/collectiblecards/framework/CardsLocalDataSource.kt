package com.ianpedraza.collectiblecards.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ianpedraza.collectiblecards.BuildConfig
import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity
import com.ianpedraza.collectiblecards.utils.Constants.FLAVOR_SUPERHEROES

object CardsLocalDataSource : CardsDataSource {

    private val superheroes = arrayOf(
        CardModel(
            name = "The Scarlet Witch",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fscarlet_witch.png?alt=media&token=4b508f8e-b653-4ebe-8c1a-a29544221827",
            rarity = Rarity.Rarity5,
            description = "Known as a “hex” in her formative years as an Avenger, the Scarlet Witch believed she used the ability to affect probabilities for a positive benefit to herself, though at times to imprecise outcomes. Later, she mastered the ability and began to understand it as a literal altering of reality.",
        ),
        CardModel(
            name = "Spiderman",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fspiderman.jpg?alt=media&token=4cafe771-4ebe-4f68-b3de-f29a618a744c",
            rarity = Rarity.Rarity5,
            description = "American teenager Peter Parker, a poor sickly orphan, is bitten by a radioactive spider. As a result of the bite, he gains superhuman strength, speed, and agility, along with the ability to cling to walls, turning him into Spider-Man.",
        ),
        CardModel(
            name = "The Winter Soldier",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fwinter-soldier.jpg?alt=media&token=1c466123-8317-428c-b898-a84e107ddb38",
            rarity = Rarity.Rarity3,
            description = "James Buchanan “Bucky” Barnes enlists to fight in World War II, but eventually literally falls in battle. Unfortunately, the evil Arnim Zola recovers him and erases his memory, turning him into a highly-trained assassin called the Winter Soldier.",
        ),
        CardModel(
            name = "Daredevil",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fdaredevil.jpg?alt=media&token=94f06635-b623-465b-af9a-14e925ef854b",
            rarity = Rarity.Rarity5,
            description = "Matthew Murdock, blinded by chemicals, is an attorney at day and vigilante by night. He is trained in the martial arts and feuds with criminals in Hell's Kitchen. As he fights convicts throughout the city, he meets new allies as well as old friends and becomes Daredevil, a symbol of justice for a corrupted city.",
        ),
        CardModel(
            name = "Doctor Strange",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fstrange.jpg?alt=media&token=142caea0-7e22-4fda-a6ce-827fe92f5f3b",
            rarity = Rarity.Rarity5,
            description = "Marvel's \"Doctor Strange\" follows the story of the talented neurosurgeon Doctor Stephen Strange who, after a tragic car accident, must put ego aside and learn the secrets of a hidden world of mysticism and alternate dimensions.",
        ),
        CardModel(
            name = "Captain America",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fcaptain_america.jpg?alt=media&token=5aade89f-c17e-43f4-9f1d-bdef66cb47b9",
            rarity = Rarity.Rarity5,
            description = "Steve Rogers was a would-be U.S. Army enlistee rejected by recruiters because of his small size. He volunteers to receive a top-secret serum and transforms into a “super-soldier.” Dubbed Captain America and clad in a red, white, and blue costume with a matching stars-and-stripes shield, Rogers joins the army.",
        ),
        CardModel(
            name = "Iron-Man",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fironman.jpg?alt=media&token=c5a6b974-1472-434b-94e8-c3e6dd7517e8",
            rarity = Rarity.Rarity5,
            description = "He is the Armored Avenger - driven by a heart that is part machine, but all hero! He is the INVINCIBLE IRON MAN! Iron Man's Powers and Abilities: Wears modular arc reactor-powered Iron Man armor, granting superhuman strength & durability, the ability to fly & project Repulsor blasts.",
        ),
        CardModel(
            name = "Black Widow",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fblack_widow.jpg?alt=media&token=ab81c469-6061-4d7e-8ed3-96960bb4c271",
            rarity = Rarity.Rarity4,
            description = "Female black widows are shiny black, with a red-orange hourglass pattern on their abdomen. Male black widows are not black, but brown or gray with small red spots. Black widows are poisonous arachnids—animals that have a skeleton outside their body, a segmented body, and eight jointed legs. They are not insects.",
        ),
        CardModel(
            name = "Hulk",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fhulk.jpg?alt=media&token=cbafe07b-6dde-404e-a0f8-b5be5d188fbc",
            rarity = Rarity.Rarity4,
            description = "He is a gigantic, green, irradiated, mutated humanoid monster with incredible strength and an inability to control his rage. The Hulk is sometimes characterized as hyper-aggressive and brutal, other times as cunning, brilliant, and scheming. He is often portrayed as an antihero.",
        ),
        CardModel(
            name = "Hawkeye",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fhawkeye.png?alt=media&token=6052e632-f812-4de3-8603-ffc7b7518a41",
            rarity = Rarity.Rarity4,
            description = "Hawkeye. An expert marksman and fighter, Clint Barton puts his talents to good use by working for S.H.I.E.L.D. as a special agent. The archer known as Hawkeye also boasts a strong moral compass that at times leads him astray from his direct orders.",
        ),
        CardModel(
            name = "Thor",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fthor.jpg?alt=media&token=43a5eaeb-4c7c-4103-b875-04d47ea75c36",
            rarity = Rarity.Rarity4,
            description = "Thor is an Asgardian warrior-prince, the God of Thunder, and a self-proclaimed protector of Earth. Thor subsequently became well known for his actions on Earth, which included acting as a founding member of the Avengers despite being the only Avenger that is not from Earth.",
        ),
        CardModel(
            name = "Ant-Man (Scott Lang)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fant_man.jpg?alt=media&token=7201475e-000a-4fa0-aef9-abf6635b8072",
            rarity = Rarity.Rarity3,
            description = "He is a reformed thief and an electronics expert. He was a member of the Avengers, the Fantastic Four and the Guardians of the Galaxy, the main character in the comic-book series FF and, in 2015, he became the title character in the series Ant-Man.",
        ),
        CardModel(
            name = "Wasp (Hope Pym)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fwasp.jpg?alt=media&token=4ae8e747-6026-4027-845e-3d289f9b8c22",
            rarity = Rarity.Rarity3,
            description = "Portrayed as the daughter of Hank Pym and Janet van Dyne, she was a senior board member of her father's company, Pym Technologies, and later inherits the superhero identity of Wasp from her mother, using a suit containing shrinking technology to shrink to the size of an insect and also fly with insect-themed wings.",
        ),
        CardModel(
            name = "Moon Knight",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fmoon_knight.jpg?alt=media&token=febfaffb-0e71-4d34-8aab-77ea977150b8",
            rarity = Rarity.Rarity3,
            description = "Marc Spector is a former United States Marine and mercenary with dissociative identity disorder. During his childhood, Spector's brother Randall drowned in a cave while they were playing, starting a strenuous and spiteful relationship with his mother, who frequently abused him.",
        ),
        CardModel(
            name = "Deadpool",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fdeadpool.jpg?alt=media&token=26c629db-9ef0-4947-9952-b76c16b922a2",
            rarity = Rarity.Rarity5,
            description = "Deadpool, whose real name is Wade Winston Wilson, is a disfigured mercenary with the superhuman ability of regeneration and physical prowess. The character is known as the \"Merc with a Mouth\" because of his tendency to talk and joke constantly, including breaking the fourth wall for humorous effect and running gags.",
        ),
        CardModel(
            name = "Wolverine",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fwolverine.jpeg?alt=media&token=afdf4153-8fc4-417b-aff8-fd7ff907f193",
            rarity = Rarity.Rarity5,
            description = "He is a mutant who possesses animal-keen senses, enhanced physical capabilities, a powerful regenerative ability known as a healing factor, and three retractable claws in each hand. Wolverine has been depicted variously as a member of the X-Men, X-Force, Alpha Flight, the Fantastic Four, and the Avengers.",
        ),
        CardModel(
            name = "Reed Richards - Mister Fantastic",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fmr_fantastic.jpg?alt=media&token=28a9d2a3-c971-430b-9a90-d75823ad409f",
            rarity = Rarity.Rarity5,
            description = "Reed Richards is a scientist and inventor, better known as Mister Fantastic, who has been considered the smartest man on Earth.",
        ),
        CardModel(
            name = "Sue Storm - Invisible Girl",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fsue_storm.jpg?alt=media&token=3305a54c-5107-4529-becb-76b714300dcc",
            rarity = Rarity.Rarity4,
            description = "Sue Storm received her powers by being exposed to a cosmic storm, and was originally known as the Invisible Girl. She possesses two powers: invisibility and force fields. Her invisibility power deals with bending light waves and allows her to render herself and other objects invisible.",
        ),
        CardModel(
            name = "Captain Marvel (Carol Danvers)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fcaptain_marvel.jpg?alt=media&token=c1356c95-66fa-4af4-8525-a6c9ad7dbd80",
            rarity = Rarity.Rarity5,
            description = "Former Air Force pilot and intelligence agent Carol Danvers pursued her dream of space exploration as a NASA employee, but her life forever changed when she was accidentally transformed into a human-Kree hybrid with extraordinary powers.",
        ),
        CardModel(
            name = "Ms Marvel",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fms_marvel.jpg?alt=media&token=98c21adc-f552-4b21-972c-2f435097e142",
            rarity = Rarity.Rarity3,
            description = " Kamala is a teenage Pakistani-American from Jersey City, New Jersey with body-morphing abilities who discovers that she has Inhuman genes in the aftermath of the \"Inhumanity\" storyline. She assumes the mantle of Ms. Marvel from her idol, Carol Danvers, after Danvers becomes Captain Marvel.",
        ),
        CardModel(
            name = "America Chavez",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Famerica_chavez.jpg?alt=media&token=d971511c-1338-4778-afa8-cdcf5f4c3f6f",
            rarity = Rarity.Rarity3,
            description = "America Chavez was born in the stars. When soaring through space, she doesn't run from a fight because she's a groundbreaking, interdimensional Super Hero. When on Earth, she punches and stomps star-shaped holes between planes of existence. When in her home country, she fights for what's right from sea to shining sea.",
        ),
        CardModel(
            name = "She-Hulk",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fshe_hulk.jpg?alt=media&token=4f845a23-8b77-4d1d-bdb5-63a4cb28279f",
            rarity = Rarity.Rarity3,
            description = "Jennifer Walters is a talented lawyer and the cousin of Bruce Banner, the Hulk. After being shot by a mobster and seriously injured, she was saved by a blood transfusion from Bruce, and his gamma-irradiated blood mutated Jennifer into the She-Hulk, kicking off her adventuring career.",
        ),
        CardModel(
            name = "Black Panther (T'Challa )",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fblack_panther.jpg?alt=media&token=36e7d6b4-f7fc-44e6-b802-048a62e4d738",
            rarity = Rarity.Rarity4,
            description = "Black Panther is the title held by T'Challa, a member of the royal family of the fictional African country of Wakanda. After the death of his father, T'Challa claimed the throne and the role of Black Panther. He was exposed to a mystical herb that enhanced his strength and agility to near-superhuman levels.",
        ),
        CardModel(
            name = "Namor",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fnamor.jpg?alt=media&token=ccbcad89-2a6e-48f9-83ff-53303a6fcdd2",
            rarity = Rarity.Rarity4,
            description = "The mutant son of a human sea captain and a prince of the mythical undersea kingdom of Atlantis, Namor possesses the superstrength and aquatic abilities of the Homo mermanus race, as well as the mutant ability of flight, along with other superhuman powers.",
        ),
        CardModel(
            name = "Shang-Chi",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fshang_chi.jpg?alt=media&token=200031a0-ca2a-4f74-9ac2-ef67a8811b67",
            rarity = Rarity.Rarity1,
            description = "A natural athlete as well as peace-loving and composed, Shang-Chi may be looked upon as a level-headed individual who practices meditation and inner-cleansing as arts within themselves. His use of chi, or internal energy, aids him not only in combat, but also in commanding his emotions in tense situations.",
        ),
        CardModel(
            name = "Vision",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fvision.jpg?alt=media&token=f1032673-7ae6-433c-b191-a39b93442396",
            rarity = Rarity.Rarity4,
            description = "Vision is an artificial intelligence, a \"synthezoid\" created by the villain Ultron and an Avenger who possesses the power to alter his density at will. Having gained a modicum of humanity, Vision is always afraid he may one day lose touch with it.",
        ),
        CardModel(
            name = "The thing",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fthe_thing.jpg?alt=media&token=1d6e5cf9-4dc0-4585-9269-2dba6459bbbc",
            rarity = Rarity.Rarity1,
            description = "Ben Grimm, better known as the Thing, is the original quintessential tough-guy of the Marvel Universe. But, because of his transformation, he's also the tragic member of the Fantastic Four. Ben was an ace test-pilot until exposure to intense cosmic radiation mutated him into a rock-skinned monster with immense superhuman strength. Ben's exterior is much harder than stone which gives him a rather gruff disposition; but deep down, he has a heart of gold.",
        ),
        CardModel(
            name = "Human Torch (Johnny Storm)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fhuman_torch.jpg?alt=media&token=a39f05aa-7616-4cc6-b971-c5b0f92fb98f",
            rarity = Rarity.Rarity2,
            description = "Jonathan \"Johnny\" Storm a.k.a. the Human Torch is an American superpowered adventurer. He was a high school student before he was exposed to high levels of cosmic radiation when his older sister Sue Storm's boyfriend, scientist Dr. Reed Richards, took them and pilot Ben Grimm into space in the stolen rocket Marvel-1.",
        ),
        CardModel(
            name = "Professor X (Charles Xavier)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fcharles_xavier.png?alt=media&token=c66d210b-257e-43da-81e5-625d4f367424",
            rarity = Rarity.Rarity5,
            description = "Charles Xavier is a mutant gifted with vast telepathic powers, being a specialist in mutant biology and sociology and the founder of the Uncanny X-Men as Professor X. With immense support from Dr. Moira MacTaggert, his life has become dedicated to fulfill his dream of mutants and humans coexisting peacefully.",
        ),
        CardModel(
            name = "Wiccan (Billy Kaplan)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fwiccan.jpg?alt=media&token=22888c16-d53a-4269-b7fb-868e0d2544be",
            rarity = Rarity.Rarity3,
            description = "Billy Kaplan, also known by his superhero aliases Asgardian and Wiccan, is a super-powered warlock and the reincarnated son of the Scarlet Witch. He has spent time on numerous super hero teams, including the Young Avengers and the New Avengers. He is married to Hulkling.",
        ),
        CardModel(
            name = "Speed",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fspeed.jpg?alt=media&token=c539156f-a926-4fd1-8a83-be0dfd0d73d9",
            rarity = Rarity.Rarity3,
            description = "Speed is a mutant with the power to move and accelerate at speeds far beyond those of normal human beings. His physiology is designed to move at high speeds which also grants him superhuman reflexes, agility, and durability.",
        ),
        CardModel(
            name = "Stature (Cassandra Lang)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fstature.PNG?alt=media&token=08255867-8aaa-4f2b-a2fc-d76206626ee6",
            rarity = Rarity.Rarity3,
            description = "She's the daughter of the second Ant man, Scott Lang, who died in an explosion outside Avengers Mansion. She may never get over his death, and currently lives with her father Penny and her new husband. Stature is the code name she adopted shortly after discovered her powers.",
        ),
        CardModel(
            name = "Kate Bishop",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fkate_bishop.jpg?alt=media&token=f9a01390-53d4-4b52-92b4-fa9018c7d2fb",
            rarity = Rarity.Rarity3,
            description = "Kate is highly skilled at archery, fencing, swordsmanship, jujitsu, gymnastics, boxing, and other forms of combat. She carries two battle staves similar to those once used by Mockingbird, a sword similar to the Swordsman's, as well as Clint Barton's bow and arrows. Black Panther had also supplied her with trick arrows.",
        ),
        CardModel(
            name = "Iron-Heart (Riri Williams)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Firon_heart.jpg?alt=media&token=f9281766-c270-4fa8-a939-ccf047e57ac6",
            rarity = Rarity.Rarity3,
            description = "Inspired by Tony Stark, AKA Iron Man, and determined to follow in his heroic footsteps, fifteen-year-old super-genius Riri Williams builds her own suit of advanced armor and leads a life of adventure, crime-fighting, and humanitarianism as the Super Hero known as Ironheart.",
        ),
        CardModel(
            name = "Cyclops",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fcyclops.jpg?alt=media&token=e9468d8d-dba0-4094-a440-128acf2744b2",
            rarity = Rarity.Rarity3,
            description = "The first X-Man, Scott Summers possesses the mutant ability to fire powerful concussive blasts through his eyes that act as a portal to another dimension full of the force that makes up his optic blast. He is visually distinctive for the ruby quartz visor he wears to control his devastating power. A born leader, Cyclops succeeded his mentor Professor X to command the X-Men.",
        ),
        CardModel(
            name = "Nick Fury",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Ffury.jpg?alt=media&token=277c68d1-c5ee-4613-8631-2acb5d6df12b",
            rarity = Rarity.Rarity4,
            description = "ury is a seasoned unarmed and armed combat expert, was a heavyweight boxer in the army (during World War II), and holds a black belt in Tae Kwon Do and a brown belt in Jiu Jitsu. He has further honed his unarmed combat skills sparring with Captain America.",
        ),
        CardModel(
            name = "Quick Silver",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fquick_silver.jpg?alt=media&token=00ac4def-b552-4c52-a9fe-4367a05e66af",
            rarity = Rarity.Rarity2,
            description = "Pietro Lensherr, a.k.a. Quicksilver, is the son of mutant supremacist Magneto and twin brother of Wanda. Magneto abandoned their human mother when the twins were children, taking them with him as he founded the Brotherhood of Mutants with Charles Xavier, whom the children regarded as an uncle.",
        ),
        CardModel(
            name = "Blade",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fblade.jpg?alt=media&token=31c7956b-049f-4ba2-947a-8fd50d5cce48",
            rarity = Rarity.Rarity1,
            description = "Blade possesses all of a vampire's strengths without their weaknesses. He has superhuman strength, stamina, and senses, as well as an accelerated healing factor, but is impervious to sunlight and vampire bites. In addition to his supernatural abilities, he's also an incredibly skilled fighter.",
        ),
        CardModel(
            name = "Nightcrawler",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fnightcrawler.png?alt=media&token=8d05d1e2-c79f-48df-9ad2-4fa873303f40",
            rarity = Rarity.Rarity2,
            description = "Nightcrawler possesses superhuman agility, the ability to teleport, and adhesive hands and feet. His physical mutations include indigo-colored velvety fur which allows him to become nearly invisible in shadows, two-toed feet, and three-fingered hands, yellow eyes, pointed ears and a prehensile tail.",
        ),
        CardModel(
            name = "Storm",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fstorm.jpg?alt=media&token=07c4dfd8-4843-41af-9266-0d01f5027823",
            rarity = Rarity.Rarity4,
            description = "Storm is one of the most powerful mutants on Earth and has demonstrated a plethora of abilities, most of which are facets of her power to manipulate the weather. Storm possesses the psionic ability to control all forms of weather over vast areas.",
        ),
        CardModel(
            name = "Rogue",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Frogue.jpg?alt=media&token=ff7a2059-57fd-4ddf-9bab-77b0fba673b6",
            rarity = Rarity.Rarity3,
            description = "Rogue is part of a subspecies of humans called mutants, who are born with superhuman abilities. Rogue has the involuntary ability to absorb and sometimes also remove the memories, physical strength, and superpowers of anyone she touches.",
        ),
        CardModel(
            name = "Colossus",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fcolossus.jpg?alt=media&token=0e756f14-7c3c-4c6d-ae13-d2ab340f3abe",
            rarity = Rarity.Rarity2,
            description = "Colossus is a mutant with the ability to transform his entire body into a form of \"organic steel\", with properties similar to osmium but of still unknown composition. Colossus must transform his entire body into this armored state; he cannot transform only a portion of his body.",
        ),
        CardModel(
            name = "Miles Morales (Spiderman)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fmiles.jpg?alt=media&token=7bdefd95-e4fd-4e70-a518-45c11e7e20ff",
            rarity = Rarity.Rarity5,
            description = "Gifted with web-shooters and guided by Peter Parker, Miles Morales uses his abilities to be a different kind of Spider-Man. In the alternate reality of Earth-1610, a young New York City teen was bitten by a genetically enhanced spider.",
        ),
        CardModel(
            name = "Nova (Richard Rider)",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fnova.jpg?alt=media&token=09fa9f44-f441-456d-b77e-ae1db92244c1",
            rarity = Rarity.Rarity4,
            description = "Richard Rider was born in Hempstead, New York. As a teenager, he was chosen at random by the alien Rhomann Dey, last surviving Centurion of the planet Xandar's elite Nova Corps, to inherit his power and succeed him in the rank of Nova Prime following the destruction of his world by the intergalactic pirate Zorr.",
        ),
        CardModel(
            name = "Wonder Man",
            image = "https://firebasestorage.googleapis.com/v0/b/superheros-b1bdc.appspot.com/o/superheros%2Fwonder_man.jpg?alt=media&token=a1efceb5-bc94-4066-8dc7-c4ce26d89ee3",
            rarity = Rarity.Rarity4,
            description = "Simon was released with help from the original Baron Zemo and his Masters of Evil. Simon agreed to undergo an experiment to give him superhuman powers, and Zemo gave him the costumed guise of Wonder Man, warning Simon that he would die without further treatments from Zemo in order to ensure his loyalty.",
        ),
    )
    private val animals = arrayOf(
        CardModel(
            name = "Cat",
            image = "https://loremflickr.com/320/240/cat",
            rarity = Rarity.Rarity1,
            description = "The cat (Felis catus) is a domestic species of small carnivorous mammal. It is the only domesticated species in the family Felidae and often referred to as the domestic cat to distinguish it from wild members of the family.",
        ),
        CardModel(
            name = "Dog",
            image = "https://loremflickr.com/320/240/dog",
            rarity = Rarity.Rarity1,
            description = "The dog (Canis lupus familiaris when considered a subspecies of the wolf or Canis familiaris when considered a distinct species) is a domesticated carnivore of the family Canidae. It is part of the wolf-like canids, and is the most widely abundant terrestrial carnivore.",
        ),
        CardModel(
            name = "Horse",
            image = "https://loremflickr.com/320/240/horse",
            rarity = Rarity.Rarity2,
            description = "The horse (Equus ferus caballus) is one of two extant subspecies of Equus ferus. It is an odd-toed ungulate mammal belonging to the taxonomic family Equidae. The horse has evolved over the past 45 to 55 million years from a small multi-toed creature, Eohippus, into the large, single-toed animal of today.",
        ),
        CardModel(
            name = "Cow",
            image = "https://loremflickr.com/320/240/cow",
            rarity = Rarity.Rarity2,
            description = "The domestic cow (or domesticated bovine) is a member of the subfamily Bovinae of the family Bovidae. The term is applied to both feral and domestic animals. The domestic cow is descended from the wild ox, a now-extinct species of bovid native to Eurasia.",
        ),
        CardModel(
            name = "Pig",
            image = "https://loremflickr.com/320/240/pig",
            rarity = Rarity.Rarity3,
            description = "The domestic pig (Sus scrofa domesticus or Sus scrofa domestica) is a domesticated descendant of the common Eurasian wild boar (Sus scrofa), a member of the pig family, Suidae. The pig is one of the most widely consumed and kept animals in the world.",
        ),
        CardModel(
            name = "Sheep",
            image = "https://loremflickr.com/320/240/sheep",
            rarity = Rarity.Rarity3,
            description = "The domestic sheep (Ovis aries) is a quadrupedal, ruminant mammal typically kept as livestock. Like all ruminants, sheep are members of the order Artiodactyla, the even-toed ungulates. Although the name \"sheep\" applies to many species in the genus Ovis, in everyday usage it almost always refers to Ovis aries.",
        ),
        CardModel(
            name = "Goat",
            image = "https://loremflickr.com/320/240/goat",
            rarity = Rarity.Rarity4,
            description = "The domestic goat (Capra aegagrus hircus) is a subspecies of goat domesticated from the wild goat of southwest Asia and Eastern Europe. The goat is a member of the animal family Bovidae and the subfamily Caprinae, meaning it is closely related to the sheep.",
        ),
        CardModel(
            name = "Chicken",
            image = "https://loremflickr.com/320/240/chicken",
            rarity = Rarity.Rarity4,
            description = "The chicken (Gallus gallus domesticus) is a type of domesticated fowl, a subspecies of the red junglefowl. It is one of the most common and widespread domestic animals, with a total population of more than 19 billion as of 2011.",
        ),
        CardModel(
            name = "Rabbit",
            image = "https://loremflickr.com/320/240/rabbit",
            rarity = Rarity.Rarity5,
            description = "The domestic rabbit (Oryctolagus cuniculus) is a small mammal belonging to the family Leporidae of the order Lagomorpha (along with the hare and the pika). Oryctolagus cuniculus includes the European rabbit species and its descendants, the world's 305 breeds of domestic rabbit.",
        ),
        CardModel(
            name = "Duck",
            image = "https://loremflickr.com/320/240/duck",
            rarity = Rarity.Rarity5,
            description = "The domestic duck (Anas platyrhynchos) is a waterfowl, a subfamily of birds that also includes swans and geese. Domestic ducks are derived from several wild species of duck, particularly the Mallard. Ducks are omnivorous birds that feed on aquatic plants, invertebrates, small fish, and small mammals.",
        ),
        CardModel(
            name = "Shark",
            image = "https://loremflickr.com/320/240/shark",
            rarity = Rarity.Rarity5,
            description = "The shark (order Selachimorpha) is a group of elasmobranch fish characterized by a cartilaginous skeleton, five to seven gill slits on the sides of the head, and pectoral fins that are not fused to the head. Modern sharks are classified within the clade Selachimorpha (or Selachii), which also includes the rays.",
        ),
        CardModel(
            name = "Tiger",
            image = "https://loremflickr.com/320/240/tiger",
            rarity = Rarity.Rarity5,
            description = "The tiger (Panthera tigris) is the largest cat species, reaching a total body length of up to 3.38 m (11.1 ft) over curves and weighing up to 306 kg (670 lb) in the wild. It is an apex predator, primarily preying on ungulates such as deer and wild boar.",
        ),
        CardModel(
            name = "Lion",
            image = "https://loremflickr.com/320/240/lion",
            rarity = Rarity.Rarity5,
            description = "The lion",
        ),
        CardModel(
            name = "Elephant",
            image = "https://loremflickr.com/320/240/elephant",
            rarity = Rarity.Rarity5,
            description = "The elephant (Elephas maximus) is a large mammal of the family Elephantidae and the order Proboscidea. Three species are currently recognised: the African bush elephant (E. maximus), the African forest elephant (E. cyclotis), and the Asian elephant (E. maximus). Elephants are scattered throughout sub-Saharan Africa, South Asia, and Southeast Asia.",
        ),
        CardModel(
            name = "Giraffe",
            image = "https://loremflickr.com/320/240/giraffe",
            rarity = Rarity.Rarity5,
            description = "The giraffe (Giraffa) is a genus of African even-toed ungulate mammals, the tallest living terrestrial animals and the largest ruminants. The genus consists of eleven or more species, which are native to sub-Saharan Africa. Giraffes are identified by their extremely long necks and legs, distinctive coat patterns, and black tongues.",
        ),
        CardModel(
            name = "Hippo",
            image = "https://loremflickr.com/320/240/hippo",
            rarity = Rarity.Rarity5,
            description = "The hippopotamus (Hippopotamus amphibius), or hippo, from ancient Greek for \"river horse\", is a large, mostly herbivorous mammal in sub-Saharan Africa, and one of only two extant species in the family Hippopotamidae, the other being the pygmy hippopotamus (Choeropsis liberiensis or Hexaprotodon liberiensis).",
        ),
        CardModel(
            name = "Rhino",
            image = "https://loremflickr.com/320/240/rhino",
            rarity = Rarity.Rarity5,
            description = "The rhinoceros (meaning \"nose horn\"), commonly abbreviated to rhino, is one of five extant species of odd-toed ungulates in the family Rhinocerotidae, as well as one of the largest surviving terrestrial animals. Two of these species are native to Africa and three to Southern Asia.",
        ),
        CardModel(
            name = "Koala",
            image = "https://loremflickr.com/320/240/koala",
            rarity = Rarity.Rarity5,
            description = "The koala (Phascolarctos cinereus), or less commonly, the koala bear, is an arboreal herbivorous marsupial native to Australia. It is the only extant representative of the family Phascolarctidae and its closest living relatives are the wombats, which comprise the family Vombatidae.",
        ),
        CardModel(
            name = "Panda",
            image = "https://loremflickr.com/320/240/panda",
            rarity = Rarity.Rarity5,
            description = "The giant panda (Ailuropoda melanoleuca), also known as panda bear or simply panda, is a bear native to south central China. It is easily recognized by the large, distinctive black patches around its eyes, over the ears, and across its round body.",
        ),
        CardModel(
            name = "Penguin",
            image = "https://loremflickr.com/320/240/penguin",
            rarity = Rarity.Rarity5,
            description = "The penguin (order Sphenisciformes, family Spheniscidae) is a group of aquatic, flightless birds. They live almost exclusively in the Southern Hemisphere, with only one species, the Galápagos penguin, found north of the equator.",
        ),
        CardModel(
            name = "Polar Bear",
            image = "https://loremflickr.com/320/240/polarbear",
            rarity = Rarity.Rarity5,
            description = "The polar bear (Ursus maritimus) is a hypercarnivorous bear whose native range lies largely within the Arctic Circle, encompassing the Arctic Ocean, its surrounding seas and surrounding land masses. It is a large bear, approximately the same size as the omnivorous Kodiak bear (Ursus arctos middendorffi).",
        ),
        CardModel(
            name = "Polar Bear",
            image = "https://loremflickr.com/320/240/polarbear",
            rarity = Rarity.Rarity5,
            description = "The polar bear (Ursus maritimus) is a hypercarnivorous bear whose native range lies largely within the Arctic Circle, encompassing the Arctic Ocean, its surrounding seas and surrounding land masses. It is a large bear, approximately the same size as the omnivorous Kodiak bear (Ursus arctos middendorffi).",
        ),
        CardModel(
            name = "Snake",
            image = "https://loremflickr.com/320/240/snake",
            rarity = Rarity.Rarity5,
            description = "A snake is a limbless, elongated, carnivorous reptile of the suborder Serpentes that can be distinguished from legless lizards by its lack of eyelids and external ears. Like all other squamates, snakes are ectothermic, amniote vertebrates covered in overlapping scales.",
        ),
    )

    private val obtained = MutableLiveData<List<CardModel>>(emptyList())

    private val data: Array<CardModel> = if (BuildConfig.FLAVOR == FLAVOR_SUPERHEROES) {
        superheroes
    } else {
        animals
    }

    private val cards = MutableLiveData(data.toList())

    override fun getAll(): LiveData<List<CardModel>> = cards

    override fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>> {
        val data = cards.value?.filter { card -> card.rarity == rarity } ?: emptyList()
        return MutableLiveData(data)
    }

    override fun getAllObtainedByRarity(rarity: Rarity?): LiveData<List<CardModel>> =
        Transformations.map(obtained) { list ->
            val data = if (rarity == null) {
                list
            } else {
                list.filter { card -> card.rarity == rarity }
            }

            data
        }

    override fun getAtPosition(position: Int): CardModel? = cards.value?.get(position)

    override fun getAllObtained(): LiveData<List<CardModel>> = obtained

    override fun isCardObtained(card: CardModel): LiveData<Boolean> =
        Transformations.map(obtained) { list ->
            list.contains(card)
        }

    override fun addObtained(card: CardModel) {
        obtained.value = obtained.value?.toMutableList()?.apply {
            add(card)
        }
    }

    override fun removeObtained(card: CardModel) {
        obtained.value = obtained.value?.toMutableList()?.apply {
            remove(card)
        }
    }
}
