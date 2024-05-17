# word2Vec_pretrained.py

from gensim.models import KeyedVectors
from nltk.tokenize import word_tokenize
import nltk
import sys
import os

# Désactiver les messages d'avertissement NLTK
nltk.download('punkt', quiet=True)

def mots_similaires(word_model, target_word, topn=1):
    try:
        similar_words = word_model.most_similar(target_word, topn=topn)
        return [word for word, _ in similar_words]
    except KeyError:
        return f"Le mot '{target_word}' n'est pas présent dans le modèle pré-entraîné."

def trouver_mots_similaires():
    # Lire le mot cible depuis les arguments de ligne de commande
    mot_cible = sys.argv[1].strip()

    # Chemin du modèle pré-entraîné
    pretrained_model_path = "/home/kechiche/eclipse-workspace/wordle/src/mots/frWac_non_lem_no_postag_no_phrase_200_skip_cut100.bin"  # Mettez à jour le chemin

    # Charger le modèle pré-entraîné
    word_model = KeyedVectors.load_word2vec_format(pretrained_model_path, binary=True)

    # Appel de la fonction mots_similaires pour obtenir les mots similaires
    result = mots_similaires(word_model, mot_cible)

    # Imprimer les mots similaires
    for word in result:
        print(word)

# Appeler la fonction principale si le script est exécuté directement
if __name__ == "__main__":
    trouver_mots_similaires()
