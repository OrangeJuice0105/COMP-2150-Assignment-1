import java.lang.annotation.*;

/**
 * This annotation is created to mark any method or class that does not require grading.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@NotForMarks
public @interface NotForMarks {
    
}
