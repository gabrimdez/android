package com.example.a36fragmentconviewmodel;
//para android X
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PetsViewModel extends ViewModel {

    //falta la clase Repositorio de PEt
    private PetsRepository mPetsRepository;


    private final MutableLiveData<Boolean> mSync = new MutableLiveData<>(false);

    //clase Pet falta
    private final LiveData<List<Pet>> mPets = Transformations.switchMap(mSync,
            new Function<Boolean, LiveData<List<Pet>>>() {
                @Override
                public LiveData<List<Pet>> apply(Boolean input) {
                    if (input) {
                        mPetsRepository.refreshPets();
                    }
                    return mPetsRepository.observePets();
                }
            });

    private final MutableLiveData<String> mPetId = new MutableLiveData<>();

    private final LiveData<Pet> mPetById = Transformations.switchMap(
            mPetId, petId -> mPetsRepository.observePet(petId));

    //la clase Event sobre esa sitring(suponemos que id de pet) hay que construirla
    private final MutableLiveData<Event<String>> mOpenPetEvent = new MutableLiveData<>();


    /**
     * Inicializar view model
     */
    public PetsViewModel(PetsRepository repository) {
        mPetsRepository = repository;
    }



    public MutableLiveData<Event<String>> getOpenPetEvent() {
        return mOpenPetEvent;
    }

    public LiveData<List<Pet>> getPets() {
        return mPets;
    }

    //cuando cliquemos en el fragmeto principal abriremosun fragmeto con un pet por su id
    public LiveData<Pet> getPetById() {
        return mPetById;
    }

    //COMO INTERACTUAMOS CON EL VIEW MODEL
    //el mas importante es clic en lista y abrir detalle



    //    Iniciar fragmento de lista > Cargar mascotas
    public void loadPets(boolean pull) {
        mSync.setValue(pull);
    }
    //    Iniciar fragmento de detalle > Cargar mascota por ID
    public void loadPetDetail(String petId) {

        // Si ya se cargó la mascota, retornar
        if (petId.equals(mPetId.getValue())) {
            return;
        }

        mPetId.setValue(petId);
    }

    //    Click en ítem e lista > Navegar a detalle
    public void openPet(String petId) {
        // Notificar acción de navegación al detalle
        mOpenPetEvent.setValue(new Event<>(petId));
    }

    //clase interna de FABRICA para coger los datos del repositorio y generar el vievmodel
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final PetsRepository mRepository;

        public Factory(PetsRepository mRepository) {
            this.mRepository = mRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PetsViewModel(mRepository);
        }

    }

}