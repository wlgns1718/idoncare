package d209.Idontcare.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginOnly {
  Level level() default Level.PARENT_OR_CHILD;
  
  enum Level{
    PARENT_ONLY,
    CHILD_ONLY,
    PARENT_OR_CHILD
  }
}

