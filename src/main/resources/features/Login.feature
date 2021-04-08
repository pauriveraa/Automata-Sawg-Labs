Feature: Autenticacion en la pagina

  Scenario: Autenticacion exitosa
    Given que un cliente potencial conoce la ruta de autenticacion
    When el usuario ingresa credenciales validas
    Then tendra una autenticacion correcta