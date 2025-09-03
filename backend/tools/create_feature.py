import os
import argparse
from typing import Tuple

def main() -> None:
    paths: Tuple[str, ...] = (
        "adapter",
        "core",
        "bootstrap",
        "adapter/api",
        "adapter/api/controller",
        "adapter/api/dto",
        "adapter/api/dto/request",
        "adapter/api/dto/response",
        "core/model",
        "core/port",
        "core/service",
    )
    root: str = "../src/main/com.thingplanner.backend/"

    parser = argparse.ArgumentParser(description="Create a feature module.")
    parser.add_argument("--name", "-n", help="Name of the module to create.")
    parser.add_argument("--type", "-t", help="Module type, e.g. Feature, Config, Tests")
    args = parser.parse_args()

    create_module(root, args.name, paths)

def create_module(root: str, module_name: str, paths: Tuple[str, ...]) -> None:
    base_path = os.path.join(root, module_name)
    os.mkdir(base_path)

    for path in paths:
        full_path = os.path.join(root, module_name, path)
        try:
            os.mkdir(full_path)
            print(f"[+] Created: {path}")
        except Exception as e:
            print(f"[!] Error creating {full_path}, {e}")

if __name__ == "__main__":
    main()