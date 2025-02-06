#!/bin/bash

# GitFlow Scripts
function git-feature-start() {
    git checkout develop
    git pull
    git checkout -b feature/$1 develop
}

function git-feature-finish() {
    git checkout develop
    git pull
    git merge --no-ff feature/$1
    git push
    git branch -d feature/$1
}

function git-release-start() {
    git checkout develop
    git pull
    git checkout -b release/$1 develop
}

function git-release-finish() {
    git checkout main
    git pull
    git merge --no-ff release/$1
    git tag -a $1 -m "Version $1"
    git push --tags

    git checkout develop
    git pull
    git merge --no-ff release/$1
    git push

    git branch -d release/$1
}
