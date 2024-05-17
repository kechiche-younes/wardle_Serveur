import xml.etree.ElementTree as ET

# Ouvrir le fichier texte en mode lecture
with open('liste_francais.txt', 'r', encoding='utf-8') as file:
    # Lire les mots du fichier texte et les stocker dans une liste
    mots_francais = file.read().splitlines()

# Créer un élément racine pour le fichier XML
root = ET.Element("mots")

# Parcourir la liste de mots français et les ajouter comme éléments dans le fichier XML
for mot in mots_francais:
    mot_element = ET.SubElement(root, "mot")
    mot_element.text = mot

# Créer un objet ElementTree à partir de l'élément racine
tree = ET.ElementTree(root)

# Enregistrer le fichier XML
tree.write("mots_francais.xml", encoding="utf-8", xml_declaration=True)

print("Le fichier XML a été créé avec succès.")
