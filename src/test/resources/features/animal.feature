# language: pt
Funcionalidade: Gerenciamento de um animal da PetStore

  Cenário: Lista somente animais disponíveis para a venda
    Dado que possuo animais available
    Quando pesquiso por todos os animais available
    Então recebo a lista de animais available
      # somente para exemplo
    E recebo outra lista de animais available

  Cenário: Lista somente animais pending
    Dado que possuo animais pending
    Quando pesquiso por todos os animais pending
    Então recebo a lista com 2 animais

  Cenário: Não lista nenhuma animal
    Dado que não possua animais sold
    Quando pesquiso por todos os animais sold
    Então recebo a lista com 0 animal

  Esquema do Cenário: Listar animais pelo seu estado de venda
    Dado que não possua animais sold
    Quando pesquiso por todos os animais <estado>
    Então recebo a lista com <quantidade> animais

    Exemplos: Animais em estoque
      | estado    | quantidade |
      | available | 7          |
      | pending   | 2          |

    Exemplos: Animais sem estoque
      | estado | quantidade |
      | sold   | 0          |

  Cenário: Listar animais disponíveis para a venda
    Dado que possuo animais available
    Quando eu pesquiso por todos os animais available
    Então recebo a lista com 7 animais available
    E 3 animais possuem o nome Lion
