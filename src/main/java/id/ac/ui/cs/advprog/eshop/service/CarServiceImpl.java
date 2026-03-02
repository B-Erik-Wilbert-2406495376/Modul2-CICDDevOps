package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import id.ac.ui.cs.advprog.eshop.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService{
    private final Repository<Car, String> carRepository;

    @Autowired
    public CarServiceImpl(Repository<Car, String> carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        if (car.getId() == null) {
            car.setId(UUID.randomUUID().toString());
        }
        return carRepository.create(car);
    }

    @Override
    public Car update(String id, Car car) {
        return carRepository.update(id, car);
    }

    @Override
    public void delete(String id) {
        carRepository.delete(id);
    }

    @Override
    public Car findById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> iterator = carRepository.findAll();
        List<Car> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }
}
