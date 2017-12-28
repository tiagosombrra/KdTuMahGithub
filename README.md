# KdTuMahGithub

Repositório para criado para desenvolver um aplciativo que consome a API do Github para exibir, através de uma pesquisa, usuários e seus respectivos repositórios de códigos.

# Estrutura de Projeto

.
├── AndroidManifest.xml
├── java
│   └── br
│       └── org
│           └── inec
│               └── kdtumahgithub
│                   ├── activity
│                   │   ├── MainActivity.java
│                   │   ├── RepositoryProfileActivity.java
│                   │   ├── UserProfileActivity.java
│                   │   └── UserSearchActivity.java
│                   ├── adapter
│                   │   ├── RepositoryArrayAdapter.java
│                   │   ├── UserArrayAdapter.java
│                   │   └── UserRepositoriesArrayAdapter.java
│                   ├── apicontrol
│                   │   └── GithubAPIManager.java
│                   ├── data
│                   │   ├── GithubRepository.java
│                   │   └── GithubUser.java
│                   └── jsonmanager
│                       └── JSONManager.java
└── res
├── drawable
│   ├── ic_launcher.png
│   ├── ic_launcher_background.xml
│   └── icon_github.png
├── drawable-v24
│   └── ic_launcher_foreground.xml
├── layout
│   ├── activity_main.xml
│   ├── activity_repository_profile.xml
│   ├── activity_user_profile.xml
│   ├── activity_user_search.xml
│   ├── repository_list_row.xml
│   ├── user_list_row.xml
│   └── user_repositories_list_row.xml
├── mipmap-anydpi-v26
│   ├── ic_launcher.xml
│   └── ic_launcher_round.xml
├── mipmap-hdpi
│   └── ic_launcher.png
├── mipmap-mdpi
│   └── ic_launcher.png
├── mipmap-xhdpi
│   └── ic_launcher.png
├── mipmap-xxhdpi
│   └── ic_launcher.png
├── mipmap-xxxhdpi
│   └── ic_launcher.png
├── values
│   ├── colors.xml
│   ├── dimens.xml
│   ├── strings.xml
│   └── styles.xml
└── values-w820dp
└── dimens.xml

# Bibliotecas e Framework

* ButterKnife
