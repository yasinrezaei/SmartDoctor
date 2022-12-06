
from django.apps import AppConfig
 
class SignalsConfig(AppConfig):
    name = 'api'
    def ready(self):
        import api.signals