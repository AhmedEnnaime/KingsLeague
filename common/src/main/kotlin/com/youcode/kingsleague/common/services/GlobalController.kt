import com.youcode.kingsleague.common.services.GlobalService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@Component
abstract class GlobalController<DTO, Identifier> {

    @Autowired
    lateinit var service: GlobalService<DTO, Identifier>

    @PostMapping
    protected fun save(@Valid @RequestBody dto: DTO): ResponseEntity<DTO?> {
        val savedDto = service.save(dto)
        return ResponseEntity(savedDto, HttpStatus.CREATED)
    }

    @GetMapping
    protected fun all(): ResponseEntity<List<DTO?>> {
        val allDto = service.getAll()
        return ResponseEntity(allDto, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    protected fun find(@PathVariable("id") id: Identifier): ResponseEntity<DTO?> {
        val foundedDto = service.findByID(id)
        return ResponseEntity(foundedDto, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    protected fun update(@PathVariable("id") id: Identifier, @Valid @RequestBody dto: DTO): ResponseEntity<DTO?> {
        val updatedDto = service.update(id, dto)
        return ResponseEntity(updatedDto, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    protected fun delete(@PathVariable("id") id: Identifier): ResponseEntity<Map<String, String>> {
        service.delete(id)
        val response: MutableMap<String, String> = HashMap()
        response["message"] = "Resource deleted successfully."
        response["deletedElementIdentifier"] = id.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
