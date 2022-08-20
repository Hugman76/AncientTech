#!/usr/bin/env python3

import os
from os import path
from pathlib import Path
import shutil

def process_file(file_path: Path, is_java: bool = False):
    print("Processing file", file_path)
    f = open(str(file_path), mode="r", encoding="utf-8")
    contents = transform_file(f.read())
    f.close()

    os.remove(str(file_path))

    # strip out old package (src/main/java/org/example/MODID)
    file_path = path.join(package_root, *file_path.parts[6:]) if is_java else file_path
    file_path = str(file_path) \
        .replace("MODID", mod_id) \
        .replace("MODCLASS", mod_class)

    f = open(file_path, mode="w+", encoding="utf-8")
    f.write(contents)
    f.close()

def transform_file(contents: str) -> str:
    contents = contents \
        .replace("org.example.MODID", package) \
        .replace("MODID", mod_id) \
        .replace("MODCLASS", mod_class) \
        .replace("MODNAME", mod_name) \
        .replace("DESCRIPTION", description) \
        .replace("GITHUB_NAME", github_name) \
        .replace("CURSEFORGE_SLUG", curseforge_slug) \
        .replace("CURSEFORGE_ID", curseforge_id) \
        .replace("MODRINTH_SLUG", modrinth_slug) \
        .replace("MODRINTH_ID", modrinth_id)
    return contents


def get_attribute(prompt: str, default: str = None) -> str:
    attr = input(prompt).strip()
    while not attr and not default:
        print("  Error: input required")
        attr = input(prompt).strip()

    return attr if attr else default


mod_id = get_attribute("Mod id (e.g universal_ores): ")
mod_class = get_attribute("Mod class name (e.g UniversalOres): ")
mod_name = get_attribute("Mod name (e.g Universal Ores): ")
description = get_attribute("Mod description: ")
package = get_attribute(
    "Package (e.g com.hugman.universal_ores, default = com.hugman.{mod_id}): ",
    f"com.hugman.{mod_id}",
)
github_name = get_attribute("Github name (e.g UniversalOres): ")
curseforge_slug = get_attribute("CurseForge slug (e.g universal-ores-fabric): ")
curseforge_id = get_attribute("CurseForge ID (e.g 489884): ")
modrinth_slug = get_attribute("Modrinth slug (e.g universal_ores): ")
modrinth_id = get_attribute("Modrinth ID (e.g 68kWHuUF): ")

java_root = path.join("src", "main", "java")
package_root = path.join(java_root, *package.split("."))
resources_root = path.join("src", "main", "resources")

os.makedirs(path.join(package_root, "config"))
os.makedirs(path.join(package_root, "init", "data"))
os.makedirs(path.join(package_root, "object", "block"))
os.makedirs(path.join(resources_root, "assets", mod_id, "blockstates"))
os.makedirs(path.join(resources_root, "assets", mod_id, "lang"))
os.makedirs(path.join(resources_root, "assets", mod_id, "models", "block"))
os.makedirs(path.join(resources_root, "assets", mod_id, "models", "item"))
os.makedirs(path.join(resources_root, "assets", mod_id, "textures", "block"))
os.makedirs(path.join(resources_root, "data", mod_id, "advancements", "recipes", "crafting"))
os.makedirs(path.join(resources_root, "data", mod_id, "loot_tables", "blocks"))
os.makedirs(path.join(resources_root, "data", mod_id, "recipes", "crafting"))
os.makedirs(path.join(resources_root, "data", mod_id, "tags", "blocks"))


def walk(target_path, level=0):
    files = []

    for file in target_path.iterdir():
        if file.is_dir():
            files.extend(walk(file, level + 1))
        else:
            if file.suffix != ".png":
                files.append(file)

    return files


for content in walk(Path(java_root)):
    process_file(content, is_java=True)

for content in walk(Path(resources_root)):
    process_file(content)

for content in walk(Path(".github")):
    process_file(content)

process_file(Path("gradle.properties"))

process_file(Path("README_NEXT.md"))
process_file(Path("CONTRIBUTING.md"))
process_file(Path("modrinth.md"))
process_file(Path("curseforge.html"))

example_assets = path.join(resources_root, "assets", "MODID")
example_data = path.join(resources_root, "data", "MODID")
org_example = path.join(java_root, "org", "example")

shutil.rmtree(path.join(example_assets))
shutil.rmtree(path.join(example_data))
shutil.rmtree(path.join(org_example, "MODID"))

os.remove("README.md")
os.rename("README_NEXT.md", "README.md")

print("Your mod has been set up!")